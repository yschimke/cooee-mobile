@file:OptIn(ExperimentalMetadataApi::class, RSocketLoggingApi::class)

package ee.coo.wear.api

import com.baulsupp.cooee.p.LogRequest
import com.baulsupp.cooee.p.TokenRequest
import com.baulsupp.cooee.p.TokenResponse
import com.squareup.moshi.Moshi
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.websocket.*
import io.ktor.utils.io.core.*
import io.rsocket.kotlin.ExperimentalMetadataApi
import io.rsocket.kotlin.RSocket
import io.rsocket.kotlin.RSocketError
import io.rsocket.kotlin.RSocketLoggingApi
import io.rsocket.kotlin.RSocketRequestHandler
import io.rsocket.kotlin.core.RSocketConnector
import io.rsocket.kotlin.keepalive.KeepAlive
import io.rsocket.kotlin.ktor.client.RSocketSupport
import io.rsocket.kotlin.ktor.client.rSocket
import io.rsocket.kotlin.logging.JavaLogger
import io.rsocket.kotlin.logging.NoopLogger
import io.rsocket.kotlin.metadata.CompositeMetadata
import io.rsocket.kotlin.metadata.RoutingMetadata
import io.rsocket.kotlin.metadata.compositeMetadata
import io.rsocket.kotlin.metadata.getOrNull
import io.rsocket.kotlin.metadata.read
import io.rsocket.kotlin.metadata.security.BearerAuthMetadata
import io.rsocket.kotlin.payload.Payload
import io.rsocket.kotlin.payload.PayloadMimeType
import io.rsocket.kotlin.payload.buildPayload
import kotlinx.coroutines.cancel
import okhttp3.Call
import okhttp3.OkHttpClient
import kotlin.time.Duration.Companion.seconds

class CooeeApiRepository(
    val client: OkHttpClient,
    val moshi: Moshi
) {
    private suspend fun buildSetupPayload(): Payload? {
        val token: String? = null

        if (token != null) {
            return buildPayload {
                compositeMetadata {
                    add(BearerAuthMetadata(token))
                }
            }
        }

        return null
    }

    suspend fun buildClient(uri: String): RSocket {
        val setupPayload = buildSetupPayload() ?: Payload.Empty

        val client = HttpClient(OkHttp) {
            engine {
                preconfigured = client
            }

            install(WebSockets)
            install(RSocketSupport) {
                connector = RSocketConnector {
                    loggerFactory = JavaLogger

                    connectionConfig {
                        setupPayload { setupPayload }
                        keepAlive = KeepAlive(5.seconds)
                        payloadMimeType = PayloadMimeType("application/json",
                            "message/x.rsocket.composite-metadata.v0")
                    }
                    acceptor {
                        RSocketRequestHandler {
                            fireAndForget {
                                val route = it.metadata?.let { route -> parseRoute(route) }

                                if (route == "log") {
                                    @Suppress("BlockingMethodInNonBlockingContext")
                                    val request = moshi.adapter(LogRequest::class.java).fromJson(it.data.readText())
                                    System.err.println("Error: ${request?.severity}] ${request?.message}")
                                } else {
                                    throw RSocketError.ApplicationError("Unknown route: $route")
                                }
                            }
                            requestResponse { rsocket ->
                                val route = rsocket.metadata?.let { route -> parseRoute(route) }

                                throw RSocketError.ApplicationError("Unknown route: $route")
                            }
                        }
                    }
                }
            }
        }

        return client.rSocket(uri, secure = uri.startsWith("wss"))
    }

    private fun parseRoute(metadata: ByteReadPacket): String? {
        return metadata.read(CompositeMetadata).getOrNull(RoutingMetadata)?.tags?.firstOrNull()
    }
}
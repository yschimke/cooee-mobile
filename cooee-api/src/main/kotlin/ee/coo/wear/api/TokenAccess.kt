@file:OptIn(ExperimentalMetadataApi::class, RSocketLoggingApi::class)

package ee.coo.wear.api

import com.baulsupp.cooee.cli.commands.buildMetadata
import com.baulsupp.cooee.p.LogRequest
import com.squareup.moshi.Moshi
import ee.coo.mobile.api.BuildConfig
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
import io.rsocket.kotlin.metadata.CompositeMetadata
import io.rsocket.kotlin.metadata.RoutingMetadata
import io.rsocket.kotlin.metadata.compositeMetadata
import io.rsocket.kotlin.metadata.getOrNull
import io.rsocket.kotlin.metadata.read
import io.rsocket.kotlin.metadata.security.BearerAuthMetadata
import io.rsocket.kotlin.payload.Payload
import io.rsocket.kotlin.payload.PayloadMimeType
import io.rsocket.kotlin.payload.buildPayload
import io.rsocket.kotlin.payload.data
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import okhttp3.OkHttpClient
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.time.Duration.Companion.seconds

interface TokenAccess {
    val idToken: String?
}
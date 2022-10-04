package com.baulsupp.cooee.cli.commands

import io.ktor.utils.io.core.ByteReadPacket
import io.rsocket.kotlin.ExperimentalMetadataApi
import io.rsocket.kotlin.RSocket
import io.rsocket.kotlin.metadata.CompositeMetadata
import io.rsocket.kotlin.metadata.RoutingMetadata
import io.rsocket.kotlin.metadata.toPacket

@OptIn(ExperimentalMetadataApi::class)
fun buildMetadata(route: String): ByteReadPacket {
  return CompositeMetadata(RoutingMetadata(route)).toPacket()
}



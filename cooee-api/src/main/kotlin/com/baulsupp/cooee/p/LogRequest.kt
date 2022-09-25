// Code generated by Wire protocol buffer compiler, do not edit.
// Source: com.baulsupp.cooee.p.LogRequest in api.proto
package com.baulsupp.cooee.p

import com.squareup.wire.FieldEncoding
import com.squareup.wire.Message
import com.squareup.wire.ProtoAdapter
import com.squareup.wire.ProtoReader
import com.squareup.wire.ProtoWriter
import com.squareup.wire.ReverseProtoWriter
import com.squareup.wire.Syntax.PROTO_3
import com.squareup.wire.WireField
import com.squareup.wire.`internal`.sanitize
import kotlin.Any
import kotlin.Boolean
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Unit
import kotlin.jvm.JvmField
import okio.ByteString

public class LogRequest(
  @field:WireField(
    tag = 1,
    adapter = "com.squareup.wire.ProtoAdapter#STRING",
    label = WireField.Label.OMIT_IDENTITY,
  )
  @JvmField
  public val message: String = "",
  @field:WireField(
    tag = 2,
    adapter = "com.baulsupp.cooee.p.LogSeverity#ADAPTER",
    label = WireField.Label.OMIT_IDENTITY,
  )
  @JvmField
  public val severity: LogSeverity = LogSeverity.FATAL,
  unknownFields: ByteString = ByteString.EMPTY,
) : Message<LogRequest, LogRequest.Builder>(ADAPTER, unknownFields) {
  public override fun newBuilder(): Builder {
    val builder = Builder()
    builder.message = message
    builder.severity = severity
    builder.addUnknownFields(unknownFields)
    return builder
  }

  public override fun equals(other: Any?): Boolean {
    if (other === this) return true
    if (other !is LogRequest) return false
    if (unknownFields != other.unknownFields) return false
    if (message != other.message) return false
    if (severity != other.severity) return false
    return true
  }

  public override fun hashCode(): Int {
    var result = super.hashCode
    if (result == 0) {
      result = unknownFields.hashCode()
      result = result * 37 + message.hashCode()
      result = result * 37 + severity.hashCode()
      super.hashCode = result
    }
    return result
  }

  public override fun toString(): String {
    val result = mutableListOf<String>()
    result += """message=${sanitize(message)}"""
    result += """severity=$severity"""
    return result.joinToString(prefix = "LogRequest{", separator = ", ", postfix = "}")
  }

  public fun copy(
    message: String = this.message,
    severity: LogSeverity = this.severity,
    unknownFields: ByteString = this.unknownFields,
  ): LogRequest = LogRequest(message, severity, unknownFields)

  public class Builder : Message.Builder<LogRequest, Builder>() {
    @JvmField
    public var message: String = ""

    @JvmField
    public var severity: LogSeverity = LogSeverity.FATAL

    public fun message(message: String): Builder {
      this.message = message
      return this
    }

    public fun severity(severity: LogSeverity): Builder {
      this.severity = severity
      return this
    }

    public override fun build(): LogRequest = LogRequest(
      message = message,
      severity = severity,
      unknownFields = buildUnknownFields()
    )
  }

  public companion object {
    @JvmField
    public val ADAPTER: ProtoAdapter<LogRequest> = object : ProtoAdapter<LogRequest>(
      FieldEncoding.LENGTH_DELIMITED, 
      LogRequest::class, 
      "type.googleapis.com/com.baulsupp.cooee.p.LogRequest", 
      PROTO_3, 
      null, 
      "api.proto"
    ) {
      public override fun encodedSize(`value`: LogRequest): Int {
        var size = value.unknownFields.size
        if (value.message != "") size += ProtoAdapter.STRING.encodedSizeWithTag(1, value.message)
        if (value.severity != LogSeverity.FATAL) size += LogSeverity.ADAPTER.encodedSizeWithTag(2,
            value.severity)
        return size
      }

      public override fun encode(writer: ProtoWriter, `value`: LogRequest): Unit {
        if (value.message != "") ProtoAdapter.STRING.encodeWithTag(writer, 1, value.message)
        if (value.severity != LogSeverity.FATAL) LogSeverity.ADAPTER.encodeWithTag(writer, 2,
            value.severity)
        writer.writeBytes(value.unknownFields)
      }

      public override fun encode(writer: ReverseProtoWriter, `value`: LogRequest): Unit {
        writer.writeBytes(value.unknownFields)
        if (value.severity != LogSeverity.FATAL) LogSeverity.ADAPTER.encodeWithTag(writer, 2,
            value.severity)
        if (value.message != "") ProtoAdapter.STRING.encodeWithTag(writer, 1, value.message)
      }

      public override fun decode(reader: ProtoReader): LogRequest {
        var message: String = ""
        var severity: LogSeverity = LogSeverity.FATAL
        val unknownFields = reader.forEachTag { tag ->
          when (tag) {
            1 -> message = ProtoAdapter.STRING.decode(reader)
            2 -> try {
              severity = LogSeverity.ADAPTER.decode(reader)
            } catch (e: ProtoAdapter.EnumConstantNotFoundException) {
              reader.addUnknownField(tag, FieldEncoding.VARINT, e.value.toLong())
            }
            else -> reader.readUnknownField(tag)
          }
        }
        return LogRequest(
          message = message,
          severity = severity,
          unknownFields = unknownFields
        )
      }

      public override fun redact(`value`: LogRequest): LogRequest = value.copy(
        unknownFields = ByteString.EMPTY
      )
    }

    private const val serialVersionUID: Long = 0L
  }
}
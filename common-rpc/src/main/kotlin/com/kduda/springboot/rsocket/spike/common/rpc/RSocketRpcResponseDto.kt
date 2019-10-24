package com.kduda.springboot.rsocket.spike.common.rpc

import java.time.Instant
import java.util.*
import com.kduda.springboot.rsocket.spike.common.rpc.InstantProtobufExtensions.toInstant

data class RSocketRpcResponseDto(val uuidRaw: UUID, val timeRaw: Instant, val valueRaw: String) :
    KotlinRepresentation<RSocketRpcResponse> {
    override fun toProto(): RSocketRpcResponse = RSocketRpcResponse.newBuilder()
        .apply {
            uuid = uuidBuilder.setValue(uuidRaw.toString()).build()
            time = timeBuilder.setSeconds(timeRaw.epochSecond)
                .setNanos(timeRaw.nano).build()
            value = valueRaw
        }.build()

    companion object : FromProtoConversion<RSocketRpcResponse, RSocketRpcResponseDto> {
        override fun from(proto: RSocketRpcResponse): RSocketRpcResponseDto =
            RSocketRpcResponseDto(
                UUID.fromString(proto.uuid.value),
                proto.time.toInstant(),
                proto.value
            )
    }
}

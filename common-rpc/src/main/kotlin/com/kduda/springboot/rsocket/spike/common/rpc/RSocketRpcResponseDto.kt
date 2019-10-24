package com.kduda.springboot.rsocket.spike.common.rpc

import java.time.Instant
import java.util.*


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
                proto.time
                    .let { Instant.ofEpochSecond(it.seconds, it.nanos.toLong()) },
                proto.value
            )
    }
}

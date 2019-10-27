package com.kduda.springboot.rsocket.spike.common.rpc

import com.kduda.springboot.rsocket.spike.common.rpc.InstantProtobufExtensions.toInstant
import com.kduda.springboot.rsocket.spike.common.rpc.InstantProtobufExtensions.toProto
import com.kduda.springboot.rsocket.spike.common.rpc.UuidProtobufExtensions.toProto
import com.kduda.springboot.rsocket.spike.common.rpc.UuidProtobufExtensions.toUuid
import java.time.Instant
import java.util.*


data class RSocketRpcResponseDto(val uuidRaw: UUID, val timeRaw: Instant, val valueRaw: String) :
    KotlinRepresentation<RSocketRpcResponse> {
    override fun toProto(): RSocketRpcResponse = RSocketRpcResponse.newBuilder()
        .apply {
            uuid = uuidRaw.toProto()
            time = timeRaw.toProto()
            value = valueRaw
        }.build()

    companion object : FromProtoConversion<RSocketRpcResponse, RSocketRpcResponseDto> {
        override fun from(proto: RSocketRpcResponse): RSocketRpcResponseDto =
            RSocketRpcResponseDto(
                proto.uuid.toUuid(),
                proto.time.toInstant(),
                proto.value
            )
    }
}

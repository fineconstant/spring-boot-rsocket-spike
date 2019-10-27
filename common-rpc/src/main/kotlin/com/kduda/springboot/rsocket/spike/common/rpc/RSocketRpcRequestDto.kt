package com.kduda.springboot.rsocket.spike.common.rpc

import com.kduda.springboot.rsocket.spike.common.rpc.UuidProtobufExtensions.toProto
import com.kduda.springboot.rsocket.spike.common.rpc.UuidProtobufExtensions.toUuid
import java.util.*


data class RSocketRpcRequestDto(val uuidRaw: UUID) : KotlinRepresentation<RSocketRpcRequest> {
    override fun toProto(): RSocketRpcRequest =
        RSocketRpcRequest.newBuilder()
            .apply { uuid = uuidRaw.toProto() }
            .build()

    companion object : FromProtoConversion<RSocketRpcRequest, RSocketRpcRequestDto> {
        override fun from(proto: RSocketRpcRequest): RSocketRpcRequestDto =
            RSocketRpcRequestDto(proto.uuid.toUuid())
    }
}

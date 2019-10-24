package com.kduda.springboot.rsocket.spike.common.rpc

import java.util.*


data class RSocketRpcRequestDto(val uuidRaw: UUID) : KotlinRepresentation<RSocketRpcRequest> {
    override fun toProto(): RSocketRpcRequest =
        RSocketRpcRequest.newBuilder()
            .apply {
                uuid = uuidBuilder.setValue(uuidRaw.toString()).build()
            }.build()

    companion object : FromProtoConversion<RSocketRpcRequest, RSocketRpcRequestDto> {
        override fun from(proto: RSocketRpcRequest): RSocketRpcRequestDto =
            RSocketRpcRequestDto(UUID.fromString(proto.uuid.value))
    }
}

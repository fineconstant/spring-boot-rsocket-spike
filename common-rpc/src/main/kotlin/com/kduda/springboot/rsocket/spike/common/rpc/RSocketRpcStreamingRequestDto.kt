package com.kduda.springboot.rsocket.spike.common.rpc

import java.util.*


data class RSocketRpcStreamingRequestDto(val streamIdRaw: Int, val uuidRaw: UUID) :
    KotlinRepresentation<RSocketRpcStreamingRequest> {
    override fun toProto(): RSocketRpcStreamingRequest {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object : FromProtoConversion<RSocketRpcStreamingRequest, RSocketRpcStreamingRequestDto> {
        override fun from(proto: RSocketRpcStreamingRequest): RSocketRpcStreamingRequestDto {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }
}

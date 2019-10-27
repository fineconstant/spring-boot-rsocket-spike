package com.kduda.springboot.rsocket.spike.common.rpc

import com.kduda.springboot.rsocket.spike.common.rpc.UuidProtobufExtensions.toProto
import com.kduda.springboot.rsocket.spike.common.rpc.UuidProtobufExtensions.toUuid
import java.util.*


data class RSocketRpcStreamingRequestDto(val streamIdRaw: Int, val uuidRaw: UUID) :
    KotlinRepresentation<RSocketRpcStreamingRequest> {
    override fun toProto(): RSocketRpcStreamingRequest =
        RSocketRpcStreamingRequest.newBuilder()
            .apply {
                this.streamId = streamIdRaw
                uuid = uuidRaw.toProto()
            }.build()


    companion object : FromProtoConversion<RSocketRpcStreamingRequest, RSocketRpcStreamingRequestDto> {
        override fun from(proto: RSocketRpcStreamingRequest): RSocketRpcStreamingRequestDto =
            RSocketRpcStreamingRequestDto(
                proto.streamId,
                proto.uuid.toUuid()
            )
    }
}

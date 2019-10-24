package com.kduda.springboot.rsocket.spike.common.rpc


data class RSocketRpcStreamingResponseDto(val streamIdRaw: Int, val responseRaw: RSocketRpcResponseDto) :
    KotlinRepresentation<RSocketRpcStreamingResponse> {
    override fun toProto(): RSocketRpcStreamingResponse {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object : FromProtoConversion<RSocketRpcStreamingResponse, RSocketRpcStreamingResponseDto> {
        override fun from(proto: RSocketRpcStreamingResponse): RSocketRpcStreamingResponseDto {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }
}

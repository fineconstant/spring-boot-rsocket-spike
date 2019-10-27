package com.kduda.springboot.rsocket.spike.common.rpc


data class RSocketRpcStreamingResponseDto(val streamIdRaw: Int, val responseRaw: RSocketRpcResponseDto) :
    KotlinRepresentation<RSocketRpcStreamingResponse> {
    override fun toProto(): RSocketRpcStreamingResponse =
        RSocketRpcStreamingResponse.newBuilder()
            .apply {
                streamId = streamIdRaw
                response = responseRaw.toProto()
            }.build()

    companion object : FromProtoConversion<RSocketRpcStreamingResponse, RSocketRpcStreamingResponseDto> {
        override fun from(proto: RSocketRpcStreamingResponse): RSocketRpcStreamingResponseDto =
            RSocketRpcStreamingResponseDto(
                proto.streamId,
                RSocketRpcResponseDto.from(proto.response)
            )
    }
}

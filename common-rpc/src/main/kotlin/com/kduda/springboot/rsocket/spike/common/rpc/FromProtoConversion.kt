package com.kduda.springboot.rsocket.spike.common.rpc

import com.google.protobuf.GeneratedMessageV3


internal interface FromProtoConversion<P : GeneratedMessageV3, O> {
    fun from(proto: P): O
}

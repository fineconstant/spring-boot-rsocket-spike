package com.kduda.springboot.rsocket.spike.producer.rpc

import com.kduda.springboot.rsocket.spike.common.rpc.RSocketRpcServiceExampleServer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.*


// TODO: configure RSocketMessageHandler to be RSocket RPC implementation
@Configuration
internal class RSocketRpcProducerConfiguration {
    @Bean
    fun rSocketRpcServiceExampleServer(): RSocketRpcServiceExampleServer =
        RSocketRpcServiceExampleServer(RSocketRpcServiceExampleAcceptor(), Optional.empty(), Optional.empty())
}

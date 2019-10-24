package com.kduda.springboot.rsocket.spike.consumer.rpc

import com.kduda.springboot.rsocket.spike.common.rpc.RSocketRpcServiceExampleClient
import io.rsocket.RSocket
import io.rsocket.RSocketFactory
import io.rsocket.transport.netty.client.TcpClientTransport
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
internal class RSocketConsumerConfiguration(private val rSocketProducer: RSocketProducerProperties) {
    @Bean
    fun rSocket() =
        RSocketFactory.connect()
            .transport(TcpClientTransport.create(rSocketProducer.host, rSocketProducer.port))
            .start()
            .block()

    @Bean
    fun rSocketRpcServiceExampleClient(rSocket: RSocket) = RSocketRpcServiceExampleClient(rSocket)
}


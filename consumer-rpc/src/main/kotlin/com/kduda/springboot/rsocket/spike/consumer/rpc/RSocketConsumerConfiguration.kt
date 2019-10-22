package com.kduda.springboot.rsocket.spike.consumer.rpc

import com.kduda.springboot.rsocket.spike.common.rpc.RSocketRpcServiceExampleClient
import io.rsocket.kotlin.RSocket
import io.rsocket.kotlin.RSocketFactory
import io.rsocket.transport.netty.client.TcpClientTransport
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.concurrent.TimeUnit


// TODO: configure
@Configuration
internal class RSocketConsumerConfiguration(private val rSocketProducer: RSocketProducerProperties) {
    @Bean
    fun rSocket() =
        RSocketFactory.connect()
            .transport {
                // WTF?!?!
                TcpClientTransport.create(rSocketProducer.host, rSocketProducer.port)
            }
            .start()
            .timeout(1, TimeUnit.SECONDS)
            .blockingGet()

    @Bean
    fun rSocketRpcServiceExampleClient(rSocket: RSocket) = RSocketRpcServiceExampleClient(rSocket)
}


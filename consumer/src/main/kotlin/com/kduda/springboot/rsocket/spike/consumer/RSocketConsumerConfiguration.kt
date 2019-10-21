package com.kduda.springboot.rsocket.spike.consumer

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.rsocket.RSocketRequester


@Configuration
internal class RSocketConsumerConfiguration(private val rSocketProducer: RSocketProducerProperties) {
    @Bean
    fun rSocketRequester(rsocketRequesterBuilder: RSocketRequester.Builder): RSocketRequester =
        rsocketRequesterBuilder.connectTcp(rSocketProducer.host, rSocketProducer.port)
            .block()
            ?: throw IllegalStateException("Could not configure RSocketRequester client")
}

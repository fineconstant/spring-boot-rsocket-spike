package com.kduda.springboot.rsocket.consumer

import io.rsocket.transport.netty.client.TcpClientTransport
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.rsocket.RSocketRequester
import org.springframework.messaging.rsocket.RSocketStrategies
import org.springframework.util.MimeTypeUtils

@Configuration
internal class RSocketConsumerConfiguration {

    @Bean
    fun rSocketRequester(rSocketStrategies: RSocketStrategies) =
        RSocketRequester.builder()
            .rsocketStrategies(rSocketStrategies)
            .dataMimeType(MimeTypeUtils.APPLICATION_JSON)
            .connect(TcpClientTransport.create(7000))
            .block()
}

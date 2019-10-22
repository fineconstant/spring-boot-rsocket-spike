package com.kduda.springboot.rsocket.spike.producer

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
internal class MessageFactoryConfiguration {
    @Bean
    fun messageFactory() = MessageFactory()
}

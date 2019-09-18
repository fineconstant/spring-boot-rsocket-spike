package com.kduda.springboot.rsocket.spike.producer

import com.kduda.springboot.rsocket.spike.common.MessageFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
internal class MessageFactoryConfiguration {
    @Bean
    fun messageFactory() = MessageFactory()
}

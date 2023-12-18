package com.example.microServDataContent.redis

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.stereotype.Component

/**
 * Simple Redis Producer for Pub/Sub Broker
 * @param injection redis template
 */
@Component
class Producer(val redisTemplate:RedisTemplate<String, Any>) {
    /**
     * simple function for send message to redis channel
     */
    fun publish(channelTopic: String, mes:String){

        redisTemplate.convertAndSend(channelTopic, mes)

    }


}
package com.example.LodInMicroServices.pub

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.stereotype.Component

/**
 * Simple Redis Producer for Pub/Sub Broker
 * @param injection redis template
 */
@Component
class Producer(val redisTemplate: RedisTemplate<String, Any>) {
    /**
     * simple function for send message to redis channel
     */
    fun publish(text:String, channel:String){
        redisTemplate.convertAndSend(channel, text)
    }
}
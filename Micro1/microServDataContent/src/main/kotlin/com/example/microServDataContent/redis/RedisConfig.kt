package com.example.microServDataContent.redis


import com.fasterxml.jackson.databind.ser.std.SerializableSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.MessageListener
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration
import org.springframework.data.redis.connection.jedis.JedisConnection
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.data.redis.listener.RedisMessageListenerContainer
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.yaml.snakeyaml.serializer.Serializer

/**
 * Redis Configuration
 */
@Configuration
class RedisConfig(val msgListener:MessageListener) {

    /**
     * Redis Host from application.properties
     */
    @Value("\${spring.data.redis.host}")
    lateinit var redisHost: String

    /**
     * Redis Port from application.properties
     */
    @Value("\${spring.data.redis.port}")
    lateinit var redisPort: String

    @Bean
    fun jedisConnFactory(): JedisConnectionFactory{
        val config = RedisStandaloneConfiguration(redisHost, redisPort.toInt())
        val jedisClientConfig = JedisClientConfiguration.builder().usePooling().build()
        val factory = JedisConnectionFactory(config, jedisClientConfig)
        factory.afterPropertiesSet()
        return factory
    }
    @Bean
    fun redisTemplate():RedisTemplate<String, Any>{
        val temp: RedisTemplate<String, Any> = RedisTemplate()

        temp.connectionFactory = jedisConnFactory()
        temp.valueSerializer = GenericJackson2JsonRedisSerializer()
        return temp
    }



    @Bean
    fun newMesListener(): MessageListenerAdapter = MessageListenerAdapter(msgListener)

    @Bean
    fun redisContainer(): RedisMessageListenerContainer {
        val container = RedisMessageListenerContainer()
        container.setConnectionFactory(jedisConnFactory())
        container.addMessageListener(newMesListener(), ChannelTopic("postMicroServicesOne"))
        return container
    }
}
package com.example.RedisConnect.servises

import com.example.RedisConnect.pub.Producer
import org.springframework.stereotype.Service

@Service
class DefService(val producer: Producer) {
     fun noti(mess: String, channel: String) {
        producer.publish(mess, channel)
    }
}
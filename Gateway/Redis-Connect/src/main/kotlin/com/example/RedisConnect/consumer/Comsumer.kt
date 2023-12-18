package com.example.RedisConnect.consumer

import com.example.RedisConnect.controller.Controller
import com.fasterxml.jackson.core.json.UTF8JsonGenerator
import com.google.gson.Gson
import org.json.JSONObject
import org.springframework.boot.autoconfigure.web.ErrorProperties.Whitelabel
import org.springframework.context.annotation.Lazy
import org.springframework.data.redis.connection.Message
import org.springframework.data.redis.connection.MessageListener
import org.springframework.stereotype.Component
/**
 * Simple Redis Consumer for Pub/Sub Broker
 *
 */
@Component
class Comsumer(@Lazy val controller:Controller): MessageListener {
    /**
     * Listener of messages
     */
    override fun onMessage(message: Message, pattern: ByteArray?) {
        var mes = message.toString().replace("\\", "")
        mes = mes.replace(Regex("^\"|\"$"), "")
        val json = JSONObject(mes)
        val cmd = json.get("Command")

        when( cmd) {
            "gotIdFromPassword" -> {
                json.remove("Command")
                println(json.getInt("id"))
                controller.userId = json.getInt("id")
            }


            "gotStock" -> {
                json.remove("Command")
                controller.jsonToApp = json
            }
            "gotAllStocks" -> {
                json.remove("Command")
                println(json)
                controller.jsonToApp = json
            }
            "gotPriceStock" -> {
                json.remove("Command")
                println(json)
                controller.jsonToApp = json
            }
            else -> {
                println("Wrong: bad cmd")
            }
        }

    }

}
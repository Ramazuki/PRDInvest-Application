package com.example.LodInMicroServices.consumer

import com.example.LodInMicroServices.pub.Producer
import com.example.LodInMicroServices.services.JsonServices
import com.example.LodInMicroServices.services.LoginService
import org.json.JSONObject

import org.springframework.context.annotation.Lazy
import org.springframework.data.redis.connection.Message
import org.springframework.data.redis.connection.MessageListener
import org.springframework.stereotype.Component
/**
 * Simple Redis Consumer for Pub/Sub Broker
 *
 */
@Component
class Consumer( @Lazy val prod: Producer, val servLogin:LoginService, val jsonServ:JsonServices): MessageListener {
    /**
     * Listener of messages
     */

    override fun onMessage(message: Message, pattern: ByteArray?) {
        var mes = message.toString().replace("\\", "")

        mes = mes.replace(Regex("^\"|\"$"), "")
        val json = JSONObject(mes)
        println(json)
        val cmd = json.get("Command")

        when( cmd) {

            "getIdFromLogin" -> {
                json.remove("Command")

                val data =  servLogin.getFromRepByLogin(json.getString("login"), json.getString("password"))
                var json = jsonServ.createJsonFromLoginDTO(data)
                json.put("Command", "gotIdFromPassword")
                prod.publish(json.toString(), "Gate")

            }

            else -> {
                println("Wrong: bad cmd")
            }
        }

    }


}
package com.example.microServDataContent.redis

import com.example.microServDataContent.controller.GateWayController
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
class Consumer(@Lazy val gate:GateWayController):MessageListener {
    /**
     * Listener of messages
     */
    override fun onMessage(message: Message, pattern: ByteArray?) {

        //clear mess

        var mes = message.toString().replace("\\", "")
        mes = mes.replace(Regex("^\"|\"$"), "")
        val json = JSONObject(mes)

        val cmd = json.get("Command")
        println(mes.toString())

        //switch case for action

        when (cmd){

            //add Stock to rep and send
            "addStock" ->{
                val userId:Int = json.getInt("userId")
                val symbol:String = json.getString("symbol")
                val number:Int = json.getInt("Number")

                gate.addApiStock(symbol, userId, number)
            }

            //show all stocks from rep

            "getAllStocks" -> {
                val userId = json.getInt("userId")
                gate.getAllStocks(userId)
            }

            //del stock from rep by id
            "delById" ->{
                val id:Int = json.getInt("id")
                val userId = json.getInt("userId")

                gate.delStockById(id, userId)
            }

            //show price of stock by symbol
            "showPriceStock" -> {
                val symbol:String = json.getString("symbol")
                gate.showPriceStock(symbol)
            }
            else ->{
                println("Wrong: bad mes!")
            }

        }
    }


}
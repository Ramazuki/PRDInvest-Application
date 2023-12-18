package com.example.microServDataContent.controller

import com.example.microServDataContent.redis.Producer
import com.example.microServDataContent.services.FinApiServices
import com.example.microServDataContent.services.StockServ
import org.json.JSONObject
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Api for manage this server
 */
@RestController
@RequestMapping("/api/v1")
class GateWayController(val stockServ:StockServ,
                        val servicesFin:FinApiServices,
                        val producer:Producer,)
{

    val topicGate:String = "Gate"



    @PostMapping("delStock/{id}")
    fun delStockById(@PathVariable id:Int, userId: Int){
        stockServ.delFromRepById(id, userId)
        println("del")

    }
    @PostMapping("getAllStocks")
    fun getAllStocks(userId: Int){
        val stocks = stockServ.getAllStocks(userId)
        stocks.put("Command", "gotAllStocks")
        producer.publish(topicGate, stocks.toString())
    }
    @PostMapping("addStock")
    fun addApiStock(symbol:String,userId:Int ,number:Int){
        val data: JSONObject = servicesFin.getApiStock(symbol, userId ,number)
        val stock = stockServ.fromJsonToStock(data)
        stockServ.addTpRepo(stock)
        data.put("Command", "gotStock")
        producer.publish(topicGate, data.toString())
    }

    @PostMapping("showPriceStock")
    fun showPriceStock(symbol: String){
        val data = servicesFin.showPrice(symbol)
        data.put("Command", "gotPriceStock")
        producer.publish(topicGate, data.toString())
    }
}
package com.example.microServDataContent.services

import com.example.microServDataContent.Model.Stock
import com.example.microServDataContent.repo.StockRepo
import org.json.JSONObject
import org.springframework.stereotype.Service

/**
 * Useful Services for manipulations with stock model
 */
@Service
class StockServ(val repoStock: StockRepo,
    val jsonServ:JsonServices) {

    //convert to stock model
    fun fromJsonToStock(json:JSONObject): Stock {
        var stock : Stock = Stock(
            userId = json.getInt("userId"),
            companyName = json.getString("companyName"),
            latestPrice = json.getInt("latestPrice"),
            totalValue = json.getInt("totalValue"))
        return stock

    }
    fun addTpRepo(stock:Stock){
        repoStock.save(stock)
    }

    fun delFromRepById(id:Int, userId:Int){
//        repoStock.deleteByIdAndUserId(id, userId)
        repoStock.deleteById(id.toLong())
    }

    fun getAllStocks(userId: Int): JSONObject {

        return jsonServ.createJsonFromList(repoStock.findAllByUserId(userId))
    }
}
package com.example.microServDataContent.services

import jakarta.persistence.criteria.CriteriaBuilder.In
import org.json.JSONObject
import org.springframework.stereotype.Service
import java.net.URL

/**
 *Useful services for finances manipulations
 *
 */
@Service
class FinApiServices {
    val apiKey = "pk_efc53ee82d5449ffa676fa6d4ed7a7e1"
    val urlGetStock = "https://api.iex.cloud/v1/data/core/quote/"

    fun getApiStock(symbol:String, userId:Int, number:Int): JSONObject {

        var response =
            URL(urlGetStock.plus(symbol).plus("?token=").plus(apiKey))
                .openStream()
                .bufferedReader()
                .use { it.readText() }

        response = response.replace(Regex("\\[|\\]"), "")

        val json = JSONObject(response)
        val jsonOut = JSONObject()
        jsonOut.put("companyName", json.getString("companyName"))
        jsonOut.put("latestPrice", json.getInt("latestPrice"))
        jsonOut.put("totalValue", number * json.getInt("latestPrice"))
        jsonOut.put("userId", userId)

        println(jsonOut)

        return jsonOut

    }
    fun showPrice(symbol: String): JSONObject {
        var response =
            URL(urlGetStock.plus(symbol).plus("?token=").plus(apiKey))
                .openStream()
                .bufferedReader()
                .use { it.readText() }

        response = response.replace(Regex("\\[|\\]"), "")

        val json = JSONObject(response)
        val jsonOut = JSONObject()
        jsonOut.put("latestPrice", json.getInt("latestPrice"))

        return jsonOut
    }

}
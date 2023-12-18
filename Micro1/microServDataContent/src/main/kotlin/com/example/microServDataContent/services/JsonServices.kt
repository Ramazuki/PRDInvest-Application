package com.example.microServDataContent.services

import com.example.microServDataContent.Model.Stock
import org.json.JSONObject
import org.springframework.stereotype.Service

/**
 * Useful services for json manipulations
 */
@Service
class JsonServices {


    public fun createJsonFromList(list:List<Stock>): JSONObject {
        val json = JSONObject()
        var counter = 0
        for(i in list){
            counter += 1
            var tmp = JSONObject()
            tmp.put("id", i.id)
            tmp.put("companyName", i.companyName)
            tmp.put("totalValue", i.totalValue)
            tmp.put("latestPrice", i.latestPrice)
            tmp.put("userId", i.userId)
            json.put(counter.toString(), tmp)
        }

        return json
    }

}
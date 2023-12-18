package com.example.RedisConnect.servises

import org.json.JSONObject
import org.springframework.boot.autoconfigure.security.SecurityProperties.User
import org.springframework.stereotype.Service

/**
 * Useful services for json manipulations
 */
@Service
class JsonServices {

    fun createJsonFroPass(login:String, password:String, cmd:String): JSONObject {
        val json = JSONObject()
        json.put("login", login)
        json.put("password", password)
        json.put("Command", cmd)

        return json
    }
    fun createJsonSymbol(symbol:String, userId:Int , number: Int, cmd:String): JSONObject {
        val json = JSONObject()
        json.put("userId", userId)
        json.put("symbol", symbol)
        json.put("Number", number)
        json.put("Command", cmd)

        return json
    }

    fun createJsonID(id:Int, userId:Int, cmd:String): JSONObject {
        val json = JSONObject()
        json.put("id", id)
        json.put("userId", userId)
        json.put("Command", cmd)
        return json
    }
}
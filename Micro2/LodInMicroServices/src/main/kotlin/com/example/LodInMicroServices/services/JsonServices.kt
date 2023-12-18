package com.example.LodInMicroServices.services

import com.example.LodInMicroServices.model.LoginDTO
import org.json.JSONObject
import org.springframework.stereotype.Service

@Service
class JsonServices {


    fun createJsonFromLoginDTO(loginDto: LoginDTO?): JSONObject {
        val json = JSONObject()
        if (loginDto != null) {
            json.put("login", loginDto.login)
        }
        if (loginDto != null) {
            json.put("id", loginDto.id)
        }
        if (loginDto != null) {
            json.put("password", loginDto.password)
        }

        return json
    }


}
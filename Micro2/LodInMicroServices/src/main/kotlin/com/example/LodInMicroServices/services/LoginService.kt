package com.example.LodInMicroServices.services

import com.example.LodInMicroServices.model.LoginDTO
import com.example.LodInMicroServices.repo.LoginRepo
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import kotlin.math.log

@Service
class LoginService(val repo:LoginRepo) {

    fun addToRepo(login: String, password: String){
        repo.save(LoginDTO(login = login, password = password))
    }

    fun getFromRepByLogin(login :String, password:String): LoginDTO? {
        var data:LoginDTO? = repo.findByLogin(login)
        if (data == null){
            addToRepo(login, password)
        }
        if (data != null) {
            if(data.login == login && data.password != password){
                println("test")
                return LoginDTO()
            }


        }
        data = repo.findByLogin(login)
        println(data)
        return data

    }
}
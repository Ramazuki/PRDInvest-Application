package com.example.LodInMicroServices.repo

import com.example.LodInMicroServices.model.LoginDTO
import org.springframework.data.repository.ListCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface LoginRepo:ListCrudRepository<LoginDTO, Long> {

    fun findByLogin(login :String):LoginDTO?
}
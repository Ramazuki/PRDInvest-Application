package com.example.myjetpackfront.data.user

import com.example.myjetpackfront.UserDto
import com.example.myjetpackfront.network.UserService


interface UsersRepository {
    suspend fun getUserInfo(userId: Int): User
}


class NetworkUserRepository(
    private val userService: UserService
): UsersRepository {
    override suspend fun getUserInfo(userId: Int): User {

        val stocksResponse = stockService.stockSearch(ticker, maxResult)
        val stocksResponse = stockService.stockSearch()

        val user = userResponse.map { data ->
            User(
                name = data.name,
                price = data.price,
                imgLink = data.imgLink
            )
        }

        return user

    }
}
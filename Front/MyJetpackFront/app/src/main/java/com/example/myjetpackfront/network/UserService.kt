package com.example.myjetpackfront.network

import com.example.myjetpackfront.Data
import retrofit2.http.GET
import com.example.myjetpackfront.UserDto
import com.example.myjetpackfront.data.user.User


interface UserService {
//    @GET("get_stocks/{ticker}")
//    suspend fun stockSearch(
//        @Path("ticker") ticker: String,
//        @Query("max_results") maxResult: Int
//    ): Stocks
//}



    @GET("users")
    suspend fun getUserInfo(): UserDto {
        return UserDto("Alex",
            arrayListOf("AAPL", "CNK", "SBER", "SHELL")
        )
    }
}
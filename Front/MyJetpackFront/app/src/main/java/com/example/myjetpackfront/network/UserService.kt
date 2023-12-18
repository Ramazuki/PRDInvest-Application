package com.example.myjetpackfront.network

import retrofit2.http.GET
import com.example.myjetpackfront.UserDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path


interface UserService {
    @GET("getUserInfo/{userId}")
    suspend fun getUserInfo(
        @Path("userId") userId: Int
    ): UserDto

    @POST("createUser")
    suspend fun createUser(
        @Body user: UserDto
    ): UserDto

    @PUT("updateUserInfo/{userId}")
    suspend fun updateUserInfo(
        @Path("userId") userId: Int,
        @Body updatedUser: UserDto
    ): UserDto

    @DELETE("deleteUser/{userId}")
    suspend fun deleteUser(
        @Path("userId") userId: Int
    ): Response<Void>
}

package com.example.myjetpackfront.network

import com.example.myjetpackfront.Stocks
import com.example.myjetpackfront.data.stock.Stock
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query


interface StockService {
    @GET("get_stocks/{ticker}")
    suspend fun getStock(
        @Path("ticker") ticker: String,
        @Query("max_results") maxResult: Int
    ): Stocks

    @POST("create_stock")
    suspend fun createStock(
        @Body stock: Stock
    ): Stocks

    @PUT("update_stock/{ticker}")
    suspend fun updateStock(
        @Path("ticker") ticker: String,
        @Body updatedStock: Stock
    ): Stocks

    @DELETE("delete_stock/{ticker}")
    suspend fun deleteStock(
        @Path("ticker") ticker: String
    ): Response<Void>
}

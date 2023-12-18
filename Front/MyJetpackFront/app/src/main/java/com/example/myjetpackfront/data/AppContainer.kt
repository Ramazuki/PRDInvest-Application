package com.example.myjetpackfront.data

import com.example.myjetpackfront.data.stock.NetworkStockRepository
import com.example.myjetpackfront.data.stock.StocksRepository
import com.example.myjetpackfront.data.user.NetworkUserRepository
import com.example.myjetpackfront.data.user.UsersRepository
import com.example.myjetpackfront.network.StockService
import com.example.myjetpackfront.network.UserService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {
    val stocksRepository: StocksRepository
    val usersRepository: UsersRepository
}

class DefaultContainer: AppContainer {
    private val STOCKS_URL = "http://localhost:8080/"
    private val USERS_URL = "http://localhost:8080/"


    private val stocksRetrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(STOCKS_URL)
        .build()

    private val stocksRetrofitService: StockService by lazy {
        stocksRetrofit.create(StockService::class.java)
    }

    override val stocksRepository: StocksRepository by lazy {
        NetworkStockRepository(stocksRetrofitService)  // Dependency injection manual
    }

    private val usersRetrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(USERS_URL)
        .build()

    private val usersRetrofitService: UserService by lazy {
        usersRetrofit.create(UserService::class.java)
    }

    override val usersRepository: UsersRepository by lazy {
        NetworkUserRepository(usersRetrofitService)  // Dependency injection manual
    }
}
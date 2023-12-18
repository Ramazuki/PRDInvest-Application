package com.example.myjetpackfront.data.stock

import com.example.myjetpackfront.Data
import com.example.myjetpackfront.Stocks
import com.example.myjetpackfront.network.StockService


interface StocksRepository {
    suspend fun getStocks(ticker: String? = null, maxResult: Int): List<Stock>
}


class NetworkStockRepository(
    private val stockService: StockService
): StocksRepository {
    override suspend fun getStocks(
        ticker: String?,
        maxResult: Int
    ): List<Stock> {
        println("Fetching stocks for ticker: $ticker, maxResult: $maxResult")

        val stocksResponse = stockService.stockSearch(ticker, maxResult)
        val stocksResponse = stockService.stockSearch()

        val stocks = stocksResponse.data.map { data ->
            Stock(
                name = data.name,
                price = data.price,
                imgLink = data.imgLink
            )
        }
        println("Fetched ${stocks.size} stocks")

        return stocks

    }
}
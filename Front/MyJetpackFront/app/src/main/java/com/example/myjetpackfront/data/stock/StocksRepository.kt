package com.example.myjetpackfront.data.stock

import androidx.compose.ui.text.toUpperCase
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

//        val stocksResponse = stockService.stockSearch(ticker, maxResult)
//        val stocksResponse = stockService.stockSearch()

//        println("Stocks response: $stocksResponse")
//        val stocksResponse = Stocks(
//            arrayListOf(
//                Data("AAPL", 123.45, "https://clck.ru/379ocV"),
//                Data("TSLA", 67.89, "https://clck.ru/379oaQ"),
//                Data("INTC", 67.89, "https://clck.ru/379odp"),
//                Data("MSFT", 67.89, "https://clck.ru/379ocV",),
//                Data("NVDA", 67.89, "https://clck.ru/379ocV")
//            )
//        )
        val stocksResponse: Stocks = ticker?.let { specifiedTicker ->
            Stocks(
                arrayListOf(
                    when (specifiedTicker.uppercase()) {
                        "AAPL" -> Data(specifiedTicker, 123.45, "https://en.wikipedia.org/wiki/Apple_Inc.")
                        "TSLA" -> Data(specifiedTicker, 67.89, "https://en.wikipedia.org/wiki/Tesla")
                        "INTC" -> Data(specifiedTicker, 45.67, "https://en.wikipedia.org/wiki/Intel")
                        "MSFT" -> Data(specifiedTicker, 89.12, "https://en.wikipedia.org/wiki/Microsoft")
                        "GOOGL" -> Data(specifiedTicker, 34.56, "https://en.wikipedia.org/wiki/Alphabet_Inc.")
                        "AMZN" -> Data(specifiedTicker, 78.90, "https://en.wikipedia.org/wiki/Amazon_(company)")
                        "FB" -> Data(specifiedTicker, 56.78, "https://en.wikipedia.org/wiki/Meta_Platforms")
                        "NFLX" -> Data(specifiedTicker, 90.12, "https://en.wikipedia.org/wiki/Netflix")
                        "GOOG" -> Data(specifiedTicker, 45.67, "https://en.wikipedia.org/wiki/Google")
                        "IBM" -> Data(specifiedTicker, 78.90, "https://en.wikipedia.org/wiki/IBM")
                        "GS" -> Data(specifiedTicker, 123.45, "https://en.wikipedia.org/wiki/Goldman_Sachs")
                        "BA" -> Data(specifiedTicker, 67.89, "https://en.wikipedia.org/wiki/Boeing")
                        "CSCO" -> Data(specifiedTicker, 34.56, "https://en.wikipedia.org/wiki/Cisco_Systems")
                        "DIS" -> Data(specifiedTicker, 56.78, "https://en.wikipedia.org/wiki/The_Walt_Disney_Company")
                        "JPM" -> Data(specifiedTicker, 78.90, "https://en.wikipedia.org/wiki/JPMorgan_Chase")
                        "PG" -> Data(specifiedTicker, 90.12, "https://en.wikipedia.org/wiki/Procter_%26_Gamble")
                        "CAT" -> Data(specifiedTicker, 45.67, "https://en.wikipedia.org/wiki/Caterpillar_Inc.")
                        "KO" -> Data(specifiedTicker, 78.90, "https://en.wikipedia.org/wiki/The_Coca-Cola_Company")
                        "MMM" -> Data(specifiedTicker, 34.56, "https://en.wikipedia.org/wiki/3M")
                        "VZ" -> Data(specifiedTicker, 56.78, "https://en.wikipedia.org/wiki/Verizon_Communications")
                        else -> Data(specifiedTicker, 0.0, "https://en.m.wikipedia.org/wiki/Clown") // Default case or handle accordingly
                    }
                )
            )
        } ?: Stocks(
            arrayListOf(
                Data("AAPL", 123.45, "https://en.wikipedia.org/wiki/Apple_Inc."),
                Data("TSLA", 67.89, "https://en.wikipedia.org/wiki/Tesla,_Inc."),
                Data("INTC", 45.67, "https://en.wikipedia.org/wiki/Intel"),
                Data("MSFT", 89.12, "https://en.wikipedia.org/wiki/Microsoft"),
                Data("GOOGL", 34.56, "https://en.wikipedia.org/wiki/Alphabet_Inc."),
                Data("AMZN", 78.90, "https://en.wikipedia.org/wiki/Amazon_(company)"),
                Data("FB", 56.78, "https://en.wikipedia.org/wiki/Meta_Platforms"),
                Data("NFLX", 90.12, "https://en.wikipedia.org/wiki/Netflix"),
                Data("GOOG", 45.67, "https://en.wikipedia.org/wiki/Google"),
                Data("IBM", 78.90, "https://en.wikipedia.org/wiki/IBM"),
                Data("GS", 123.45, "https://en.wikipedia.org/wiki/Goldman_Sachs"),
                Data("BA", 67.89, "https://en.wikipedia.org/wiki/Boeing"),
                Data("CSCO", 34.56, "https://en.wikipedia.org/wiki/Cisco_Systems"),
                Data("DIS", 56.78, "https://en.wikipedia.org/wiki/The_Walt_Disney_Company"),
                Data("JPM", 78.90, "https://en.wikipedia.org/wiki/JPMorgan_Chase"),
                Data("PG", 90.12, "https://en.wikipedia.org/wiki/Procter_%26_Gamble"),
                Data("CAT", 45.67, "https://en.wikipedia.org/wiki/Caterpillar_Inc."),
                Data("KO", 78.90, "https://en.wikipedia.org/wiki/The_Coca-Cola_Company"),
                Data("MMM", 34.56, "https://en.wikipedia.org/wiki/3M"),
                Data("VZ", 56.78, "https://en.wikipedia.org/wiki/Verizon_Communications")
            )
        )

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
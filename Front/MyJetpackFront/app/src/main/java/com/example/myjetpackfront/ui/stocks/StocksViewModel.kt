package com.example.myjetpackfront.ui.stocks

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.myjetpackfront.MyApplication
import com.example.myjetpackfront.data.stock.Stock
import com.example.myjetpackfront.data.stock.StocksRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface StocksUiState {
    data class Success(val stockSearch: List<Stock>): StocksUiState
    object Error: StocksUiState
    object Loading: StocksUiState
}

class StocksViewModel(
    private val stocksRepository: StocksRepository
): ViewModel(){

    var stocksUiState: StocksUiState by mutableStateOf(StocksUiState.Loading)
         private set

    private val _searchWidgetState: MutableState<SearchWidgetState> =
        mutableStateOf(value = SearchWidgetState.CLOSED)
    val searchWidgetState: State<SearchWidgetState> = _searchWidgetState

    private val _searchTextState: MutableState<String> =
        mutableStateOf(value = "")
    val searchTextState: State<String> = _searchTextState

    fun updateSearchWidgetState(newValue: SearchWidgetState) {
        _searchWidgetState.value = newValue
    }

    fun updateSearchTextState(newValue: String) {
        _searchTextState.value = newValue
    }

    init {
        getStocks( )
    }

    fun getStocks(ticker: String? = null, maxResults: Int = 40) {
        viewModelScope.launch {
            stocksUiState = StocksUiState.Loading
            stocksUiState = try {
                StocksUiState.Success(stocksRepository.getStocks(ticker, maxResults))
            } catch (e: IOException){
                StocksUiState.Error
            } catch (e: HttpException){
                StocksUiState.Error
            }

        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val myApplication = (this[APPLICATION_KEY] as MyApplication)
                val stocksRepository = myApplication.container.stocksRepository
                StocksViewModel(stocksRepository = stocksRepository)
            }
        }
    }
}

enum class SearchWidgetState {
    OPENED,
    CLOSED
}

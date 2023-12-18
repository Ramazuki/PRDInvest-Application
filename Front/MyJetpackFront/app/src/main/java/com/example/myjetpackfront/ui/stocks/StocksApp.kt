package com.example.myjetpackfront.ui.stocks

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myjetpackfront.data.stock.Stock
import com.example.myjetpackfront.ui.screens.HomeScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StocksApp(
    modifier: Modifier = Modifier,
    onStockClick: (Stock) -> Unit
){
    val stocksViewModel: StocksViewModel = viewModel(factory = StocksViewModel.Factory)
    val searchWidgetState = stocksViewModel.searchWidgetState
    val searchTextState = stocksViewModel.searchTextState

    val profileViewModel: ProfileViewModel = viewModel(factory = ProfileViewModel.Factory)

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            MainAppBar(
                searchWidgetState = searchWidgetState.value,
                searchTextState = searchTextState.value,
                onTextChange = {
                    stocksViewModel.updateSearchTextState(newValue = it)
                },
                onCloseClicked = {
                    stocksViewModel.updateSearchWidgetState(newValue = SearchWidgetState.CLOSED)
                },
                onSearchClicked = {
                    stocksViewModel.getStocks(it)
                },
                onSearchTriggered = {
                    stocksViewModel.updateSearchWidgetState(newValue = SearchWidgetState.OPENED)
                },
            )
        }
    ) {
        Surface(modifier = Modifier
            .fillMaxSize()
            .padding(it),
            color = MaterialTheme.colorScheme.background
        ) {
            HomeScreen(
                stocksUiState = stocksViewModel.stocksUiState,
                profileUiState = profileViewModel.profileUiState,
                retryAction = {stocksViewModel.getStocks()},
                onStockClick = onStockClick
            )
        }
    }
}
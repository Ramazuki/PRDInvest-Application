package com.example.myjetpackfront.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myjetpackfront.data.stock.Stock
import com.example.myjetpackfront.ui.stocks.ProfileUiState
import com.example.myjetpackfront.ui.stocks.StocksUiState

@Composable
fun HomeScreen(
    stocksUiState: StocksUiState,
    profileUiState: ProfileUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onStockClick: (Stock) -> Unit
) {
    var isProfileDialogOpen by remember { mutableStateOf(false) }

    Box(modifier = modifier.fillMaxSize()) {
        when (stocksUiState) {
            is StocksUiState.Loading -> LoadingScreen(modifier)
            is StocksUiState.Success -> StocksGridScreen(
                stocks = (stocksUiState as? StocksUiState.Success)?.stockSearch ?: emptyList(),
                modifier = modifier,
                onStockClick = onStockClick
            )
            is StocksUiState.Error -> ErrorScreen(retryAction = retryAction)
        }

        ProfileIcon(onClick = {
            isProfileDialogOpen = true
        })
        // Диалог с профилем
        if (isProfileDialogOpen) {
            AlertDialog(
                onDismissRequest = {
                    isProfileDialogOpen = false
                },
                title = { },
                text = {
                    ProfileScreen(
                        userInfo = (profileUiState as ProfileUiState.Success).getUserInfo,
                        modifier = Modifier
                            .padding(16.dp)
                            .background(MaterialTheme.colorScheme.background)
                    )
                },
                confirmButton = {
                    // Можете добавить кнопки или другие элементы управления здесь
                }
            )
        }
    }
}

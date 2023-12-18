package com.example.myjetpackfront.ui.screens


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.myjetpackfront.R
import com.example.myjetpackfront.data.stock.Stock
import com.example.myjetpackfront.navigation.AppRouter
import com.example.myjetpackfront.navigation.ButtonHandler
import com.example.myjetpackfront.navigation.Screens

@Composable
fun StocksGridScreen(
    stocks: List<Stock>,
    modifier: Modifier,
    onStockClick: (Stock) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        contentPadding = PaddingValues(4.dp),
    ) {
        itemsIndexed(stocks) { _, stock ->
            StocksCard(stock = stock, modifier, onStockClick)
        }
    }
    ButtonHandler {
        AppRouter.navigateTo(Screens.HomeScreens)
    }
}

@Composable
fun StocksCard(
    stock: Stock,
    modifier: Modifier,
    onStockClick: (Stock) -> Unit
) {
    Card(
        modifier = modifier
            .padding(4.dp)
            .fillMaxWidth()
            .requiredHeight(296.dp)
            .clickable {onStockClick(stock)},
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            stock.name?.let {
                Text(
                    text = it,
                    textAlign = TextAlign.Center,
                    modifier = modifier
                        .padding(top = 4.dp, bottom = 8.dp)
                )
            }
            AsyncImage(
                modifier = modifier.fillMaxWidth(),
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(stock.imgLink?.replace("http", "https"))
                    .crossfade(true)
                    .build(),
                error = painterResource(id = R.drawable.stock_error),
                placeholder = painterResource(id = R.drawable.loading_img),
                contentDescription = stringResource(id = R.string.content_description),
                contentScale = ContentScale.Crop

            )
        }
    }
}
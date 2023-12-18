package com.example.myjetpackfront

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.content.ContextCompat
import com.example.myjetpackfront.ui.stocks.StocksApp
import com.example.myjetpackfront.ui.theme.MyJetpackFrontTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyJetpackFrontTheme {
                StocksApp(
                    onStockClick = {
                        ContextCompat.startActivity(
                            this,
                            Intent(Intent.ACTION_VIEW, Uri.parse(it.imgLink)),
                            null
                        )
                    }
                )
            }
        }
    }
}

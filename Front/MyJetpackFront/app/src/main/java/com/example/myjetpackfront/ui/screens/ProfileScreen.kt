package com.example.myjetpackfront.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myjetpackfront.ui.theme.MyJetpackFrontTheme
import com.example.myjetpackfront.R
import com.example.myjetpackfront.data.user.User
import com.example.myjetpackfront.navigation.AppRouter
import com.example.myjetpackfront.navigation.Screens


@Composable
fun ProfileIcon(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .clickable { onClick() }
            .background(Color.Cyan, shape = CircleShape)
    ) {
        IconButton(onClick = { onClick() }) {
            Icon(
                imageVector = Icons.Filled.Person,
                contentDescription = "IconProfile",
                tint = Color.Black
            )
        }
    }
}


@Composable
fun ProfileScreen(
    userInfo: User,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Profile Image
        Image(
            painter = painterResource(id = R.drawable.profile_image),
            contentDescription = null,
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary)
                .padding(16.dp)
        )

        // User Info
        Spacer(modifier = Modifier.height(16.dp))
        userInfo.name?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Tickers
        userInfo.tickers?.let { tickers ->
            Text(
                text = tickers.joinToString("\n"), // or use any other separator you prefer
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
            )
        }


        Spacer(modifier = Modifier.height(32.dp))

        // Button to navigate to the Home screen
        Button(
            onClick = { AppRouter.navigateTo(Screens.HomeScreens) },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(text = "Go to Home")
        }
    }
}


@Preview
@Composable
fun ProfileScreenPreview() {
    MyJetpackFrontTheme {
        ProfileScreen(
            userInfo = User("SomeName", arrayListOf("asd", "adasd"))
        )
    }
}

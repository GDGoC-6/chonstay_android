package com.example.chonstay_android.ui.Customer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage

@Composable
fun StayDetailScreen(title: String, location: String, star: String) {
    Column(modifier = Modifier.fillMaxSize()) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .background(Color.Gray),
            model = "",
            contentDescription = ""
        )

        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(title, style = TextStyle(fontSize = 24.sp))
            Text(location, style = TextStyle(fontSize = 24.sp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = ""
                )
                Text(star, style = TextStyle(fontSize = 24.sp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StayDetailPreview() {
    StayDetailScreen("촌스테이", "아", "4")
}
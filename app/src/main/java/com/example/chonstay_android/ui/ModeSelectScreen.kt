package com.example.chonstay_android.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.chonstay_android.R

@Composable
fun ModeSelectScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.chonstay_logo),
            contentDescription = "촌스테이",
            modifier = Modifier
                .fillMaxWidth(0.4f)
                .aspectRatio(1f)
        )
        Spacer(Modifier.height(20.dp))

        Text(
            text = "해당하는 선택지를 골라주세요",
            style = TextStyle(fontSize = 24.sp)
        )
        Spacer(Modifier.height(16.dp))

        Surface(
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .aspectRatio(1f)
                .border(1.dp, Color.Black, RoundedCornerShape(16.dp))
                .clickable { navController.navigate("OwnerTabScreen") },
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) { Text(text = "주최자") }
        }
        Spacer(Modifier.height(8.dp))
        Surface(
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .aspectRatio(1f)
                .border(1.dp, Color.Black, RoundedCornerShape(16.dp))
                .clickable { navController.navigate("CustomerTabScreen") },
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) { Text(text = "참여자") }
        }
        Spacer(Modifier.height(80.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun ModeSelectPreview() {
    ModeSelectScreen(rememberNavController())
}
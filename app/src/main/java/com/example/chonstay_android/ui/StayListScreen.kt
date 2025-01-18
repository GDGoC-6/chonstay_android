package com.example.chonstay_android.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImage

@Composable
fun StayListScreen(navController: NavController, location: String) {
    var locationText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BasicTextField(
            value = locationText,
            onValueChange = { locationText = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(40.dp)
                .background(Color(230, 230, 230, 255), RoundedCornerShape(16.dp))
                .border(1.dp, Color.Transparent, RoundedCornerShape(16.dp)),
            decorationBox = {
                Box {
                    if (locationText.isEmpty()) {
                        Text(
                            text = "지역, 키워드를 검색하세요",
                            color = Color.Gray,
                            modifier = Modifier
                                .align(Alignment.CenterStart)
                                .padding(start = 16.dp)
                        )
                    }
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "",
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .padding(end = 16.dp)
                    )
                    it()
                }
            }
        )

        ScrollableLocationList(location)

        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .padding(16.dp)
                .border(0.dp, Color.Transparent, RoundedCornerShape(16.dp))
                .background(Color.Gray, RoundedCornerShape(16.dp)),
            model = "",
            contentDescription = ""
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(text = "춘스테이")
                Text(text = "충청남도 부여군 부여읍 신기정로94번길 17-6")
            }

            Row {  }
        }
    }
}

@Composable
fun ScrollableLocationList(location: String) {
    val locations = listOf(
        "서울", "부산", "대구", "인천", "광주", "대전",
        "울산", "세종", "경기도", "강원도", "충청북도", "충청남도",
        "전라북도", "전라남도", "경상북도", "경상남도", "제주도"
    )
    var selectedLocation by remember { mutableStateOf(location) }

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 16.dp, end = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(locations) { location ->
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(
                        if (location == selectedLocation) Color(107, 142, 35)
                        else Color(192, 200, 176)
                    )
                    .clickable { selectedLocation = location }
                    .padding(horizontal = 24.dp, vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = location,
                    color = if (location == selectedLocation) Color.White
                    else Color.Black,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StayListPreview() {
    StayListScreen(rememberNavController(), "서울")
}
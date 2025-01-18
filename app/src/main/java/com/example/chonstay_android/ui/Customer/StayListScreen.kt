package com.example.chonstay_android.ui.Customer

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.chonstay_android.HomePreview
import com.example.chonstay_android.Value
import com.example.chonstay_android.getHomePreviews

@Composable
fun StayListScreen(navController: NavController, location: String) {
    var locationText by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        getHomePreviews(location) { success, previews ->
            if (success) {
                Value.stayList = previews ?: emptyList()
                Log.d("HomePreview", "데이터 로드 성공: ${Value.stayList}")
            } else {
                Value.stayList = emptyList()
                Log.d("HomePreview", "데이터 로딩 실패")
            }
            isLoading = false
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
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

        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(Value.stayList) { stay ->
                    StayItem(
                        stay = stay,
                        onClick = {
                            navController.navigate(
                                "StayDetailScreen/${stay.homeName}/${stay.address}/${stay.averageReview}"
                            )
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun StayItem(stay: HomePreview, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color.LightGray)
            .clickable { onClick() }
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = stay.homeName,
                style = TextStyle(fontSize = 18.sp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stay.address,
                style = TextStyle(fontSize = 14.sp, color = Color.DarkGray)
            )
        }
        Text(
            text = String.format("%.1f", stay.averageReview),
            style = TextStyle(fontSize = 16.sp, color = Color.Black)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun StayListPreview() {
    StayListScreen(rememberNavController(), "서울")
}

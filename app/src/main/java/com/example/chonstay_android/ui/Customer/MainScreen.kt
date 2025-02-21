package com.example.chonstay_android.ui.Customer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.chonstay_android.R

@Composable
fun MainScreen(navController: NavController) {
    var locationText by remember { mutableStateOf("") }
    val locations = listOf(
        "부산" to R.drawable.busan,
        "대구" to R.drawable.daegu,
        "인천" to R.drawable.incheon,
        "광주" to R.drawable.gwangju,
        "대전" to R.drawable.daejeon,
        "울산" to R.drawable.ulsan,
        "세종" to R.drawable.sejong,
        "경기도" to R.drawable.gyeonggido,
        "강원도" to R.drawable.gangwondo,
        "충청북도" to R.drawable.chungcheongbugdo,
        "충청남도" to R.drawable.chungcheongnamdo,
        "전라북도" to R.drawable.jeonlabugdo,
        "전라남도" to R.drawable.jeonlanamdo,
        "경상북도" to R.drawable.gyeongsangbugdo,
        "경상남도" to R.drawable.gyeongsangnamdo,
        "제주도" to R.drawable.jejudo
    )

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

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(locations) { location ->
                LocationItem(navController, location.first, location.second)
            }
        }
    }
}

@Composable
fun LocationItem(navController: NavController, location: String, image: Int) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .background(Color.Transparent, RoundedCornerShape(16.dp))
            .clickable { navController.navigate("StayListScreen/${location}")},
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = location,
            style = TextStyle(
                fontSize = 12.sp,
                color = Color.Black
            ),
            modifier = Modifier.padding(horizontal = 8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    MainScreen(rememberNavController())
}

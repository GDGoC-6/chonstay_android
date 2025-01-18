package com.example.chonstay_android.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImage
import com.example.chonstay_android.R

@Composable
fun OnBoardingScreen(navController: NavController) {
    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

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

        Column(modifier = Modifier.fillMaxSize(0.8f),) {
            Column {
                Text(
                    text = "안녕하세요",
                    style = TextStyle(fontSize = 24.sp),
                )
                Text(
                    text = "촌스테이를 시작해보세요",
                    style = TextStyle(fontSize = 24.sp)
                )
            }

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {
                OutlinedTextField(
                    value = id,
                    onValueChange = { id = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "아이디를 입력하세요") }
                )
                Spacer(Modifier.height(16.dp))
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "비밀번호를 입력하세요") },
                    visualTransformation = PasswordVisualTransformation()
                )
            }
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Button(
                modifier = Modifier.fillMaxWidth(0.8f),
                onClick = { navController.navigate("ModeSelect") },
                colors = ButtonDefaults.buttonColors(
                    Color(107, 142, 35),
                    Color.White
                )
            ) {
                Text(
                    text = "로그인",
                    style = TextStyle(fontSize = 18.sp)
                )
            }

            Text(
                text = "회원가입 하시겠습니까?",
                style = TextStyle(fontSize = 12.sp),
                modifier = Modifier
                    .clickable {
//                navController.navigate("")
                    }
                    .padding(top = 8.dp, bottom = 40.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnBoardingPreview() {
    OnBoardingScreen(rememberNavController())
}
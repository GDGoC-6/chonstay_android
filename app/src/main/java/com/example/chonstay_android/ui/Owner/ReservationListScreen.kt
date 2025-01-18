package com.example.chonstay_android.ui.Owner

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage

@ExperimentalLayoutApi
@Composable
fun ReservationListScreen() {
    val isStay by remember { mutableStateOf(false) }

    if (!isStay) {
        var addStay by remember { mutableStateOf(false) }

        if (addStay) {
            val availablePrograms = listOf("김장", "딸기 수확", "산나물 채취", "농촌 체험", "지역 축제 기획")
            val availableItems = listOf("수고비", "따뜻한 식사", "딸기 제공", "농촌의 생활")
            val selectedPrograms = remember { mutableStateListOf<String>() }
            val selectedItems = remember { mutableStateListOf<String>() }
            var personCount by remember { mutableFloatStateOf(1f) }
            val scrollState = rememberScrollState()
            val maxPersons = 10
            var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
            var name by remember { mutableStateOf("") }
            var content by remember { mutableStateOf("") }

            val galleryLauncher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.GetContent(),
                onResult = { uri -> if (uri != null) selectedImageUri = uri }
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(scrollState),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                Text(text = "촌스테이 이름")
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = {
                        Text(
                            text = "이름을 입력해주세요",
                            style = TextStyle(Color.LightGray)
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Text(text = "지역")
                FlowRow {
                    val locations = listOf(
                        "부산", "대구", "인천", "광주", "대전",
                        "울산", "세종", "경기도", "강원도", "충청북도", "충청남도",
                        "전라북도", "전라남도", "경상북도", "경상남도", "제주도"
                    )
                    var selectedOption by remember { mutableStateOf<String?>(null) }

                    locations.forEach { location ->
                        SelectableChip(
                            text = location,
                            isSelected = selectedOption == location,
                            onClick = {
                                selectedOption = location
                            }
                        )
                    }
                }

                Text(text = "프로그램")
                FlowRow {
                    availablePrograms.forEach { program ->
                        SelectableChip(
                            text = program,
                            isSelected = selectedPrograms.contains(program),
                            onClick = {
                                if (selectedPrograms.contains(program)) {
                                    selectedPrograms.remove(program)
                                } else {
                                    selectedPrograms.add(program)
                                }
                            }
                        )
                    }
                }

                if (selectedPrograms.isNotEmpty()) {
                    Text("선택된 프로그램: ${selectedPrograms.joinToString(", ")}")
                }

                Text(text = "숙소 제공")
                FlowRow {
                    val options = listOf("예", "아니요")
                    var selectedOption by remember { mutableStateOf<String?>(null) }

                    options.forEach { option ->
                        SelectableChip(
                            text = option,
                            isSelected = selectedOption == option,
                            onClick = {
                                selectedOption = option
                            }
                        )
                    }
                }

                Text(text = "제공하는 것")
                FlowRow {
                    availableItems.forEach { item ->
                        SelectableChip(
                            text = item,
                            isSelected = selectedItems.contains(item),
                            onClick = {
                                if (selectedItems.contains(item)) {
                                    selectedItems.remove(item)
                                } else {
                                    selectedItems.add(item)
                                }
                            }
                        )
                    }
                }

                if (selectedItems.isNotEmpty()) {
                    Text("선택된 제공 항목: ${selectedItems.joinToString(", ")}")
                }

                Text(text = "인원수")
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Slider(
                        value = personCount,
                        onValueChange = { personCount = it },
                        valueRange = 1f..maxPersons.toFloat(),
                        steps = maxPersons - 1,
                        modifier = Modifier.weight(1f)
                    )
                    Text(text = "${personCount.toInt()}명")
                }

                selectedImageUri?.let { uri ->
                    AsyncImage(
                        model = uri,
                        contentDescription = "선택한 이미지",
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f)
                            .background(Color.Gray, RoundedCornerShape(16.dp))
                    )
                }

                Button(
                    onClick = { galleryLauncher.launch("image/*") },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("사진 업로드")
                }

                Text(text = "설명글")
                OutlinedTextField(
                    value = content,
                    onValueChange = { content = it },
                    label = {
                        Text(
                            text = "설명글을 입력해주세요",
                            style = TextStyle(Color.LightGray)
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                )

                Button(
                    onClick = {  },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("등록하기")
                }
                Spacer(Modifier.height(80.dp))
            }
        } else {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = "등록된 스테이가 없습니다.",
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(top = 80.dp),
                    style = TextStyle(fontSize = 24.sp)
                )
                Button(
                    modifier = Modifier
                        .fillMaxSize(0.8f)
                        .aspectRatio(4 / 1f)
                        .align(Alignment.Center),
                    onClick = { addStay = true },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(107, 142, 35),
                        contentColor = Color.White
                    )
                ) { Text("등록하시겠습니까?") }
            }
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(0.8f)
            ) { Text(text = "내가 받은 예약 리스트") }

            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .aspectRatio(1f)
                    .background(Color.Gray),
                model = "",
                contentDescription = ""
            )

            Column(
                modifier = Modifier.padding(8.dp)
            ) {

            }
        }
    }
}


@Composable
fun SelectableChip(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier.clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        color = if (isSelected) Color(107, 142, 35) else Color(230, 240, 230),
        border = BorderStroke(1.dp, Color.Gray)
    ) {
        Text(
            text = text,
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 8.dp),
            color = if (isSelected) Color.White else Color.Black
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Preview(showBackground = true)
@Composable
fun ReservationListPreview() {
    ReservationListScreen()
}
package com.example.chonstay_android.ui.Owner

import android.net.Uri
import android.widget.Toast
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
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import coil3.compose.AsyncImage

@ExperimentalLayoutApi
@Composable
fun ReservationListScreen() {
    val isStay by remember { mutableStateOf(false) }

    if (!isStay) {
        var addStay by remember { mutableStateOf(false) }

        if (addStay) {
            val context = LocalContext.current
            val availablePrograms = listOf("김장", "딸기 수확", "산나물 채취", "농촌 체험", "지역 축제 기획")
            val availableItems = listOf("수고비", "따뜻한 식사", "딸기 제공", "농촌의 생활")
            val selectedPrograms = remember { mutableStateListOf<String>() }
            val selectedItems = remember { mutableStateListOf<String>() }
            var personCount by remember { mutableFloatStateOf(1f) }
            var showCameraPermissionDialog by remember { mutableStateOf(false) }
            val scrollState = rememberScrollState()
            val maxPersons = 10
            var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

            val requestPermissionLauncher = rememberUpdatedState(
                ActivityResultContracts.RequestPermission()
            )

            // 갤러리 선택 결과를 처리하는 launcher
            val pickImageLauncher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.PickVisualMedia(),
                onResult = { uri ->
                    uri?.let {
                        selectedImageUri = it
                    }
                }
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
                    value = "",
                    onValueChange = {},
                    label = { Text("이름을 입력해주세요") },
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
                                selectedOption = location // 선택 시 해당 옵션으로 변경
                            }
                        )
                    }
                }
                // 1. 프로그램 선택
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

                // "예"와 "아니요" 중 하나만 선택 가능하도록 수정된 코드
                Text(text = "숙소 제공")
                FlowRow {
                    val options = listOf("예", "아니요")
                    var selectedOption by remember { mutableStateOf<String?>(null) }

                    options.forEach { option ->
                        SelectableChip(
                            text = option,
                            isSelected = selectedOption == option,
                            onClick = {
                                selectedOption = option // 선택 시 해당 옵션으로 변경
                            }
                        )
                    }
                }

                // 선택된 프로그램 표시
                if (selectedPrograms.isNotEmpty()) {
                    Text("선택된 프로그램: ${selectedPrograms.joinToString(", ")}")
                }

                // 2. 제공하는 것 선택
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

                // 선택된 제공 항목 표시
                if (selectedItems.isNotEmpty()) {
                    Text("선택된 제공 항목: ${selectedItems.joinToString(", ")}")
                }

                // 3. 인원수 선택
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

                Button(
                    onClick = { requestPermission() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("사진 업로드")
                }

                Text(text = "설명글")
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    label = { Text("설명글을 입력해주세요") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                )
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

fun requestPermission() {
    if (ContextCompat.checkSelfPermission(
            LocalContext.current,
            Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    ) {
        // 권한이 이미 있다면 바로 갤러리로 이동
        pickImageLauncher.launch(ActivityResultContracts.PickVisualMedia.Requester.PhotoPicker)
    } else {
        // 권한이 없다면 권한 요청
        requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
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

@Composable
fun CameraPermissionDialog(onResult: (Boolean) -> Unit) {
    AlertDialog(
        onDismissRequest = { onResult(false) },
        title = { Text("카메라 권한 요청") },
        text = { Text("이 앱이 카메라를 사용하려면 권한이 필요합니다.") },
        confirmButton = {
            TextButton(onClick = { onResult(true) }) { Text("허용") }
        },
        dismissButton = {
            TextButton(onClick = { onResult(false) }) { Text("취소") }
        }
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Preview(showBackground = true)
@Composable
fun ReservationListPreview() {
    ReservationListScreen()
}
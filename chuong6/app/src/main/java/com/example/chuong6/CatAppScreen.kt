package com.example.chuong6

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CatAppScreen(navController: NavController) {
    val context = LocalContext.current
    val cats = remember {
        mutableStateListOf(
            Cat(name = "Fred", description = "Silent and deadly", imageRes = R.drawable.anh1),
            Cat(name = "Mimi", description = "Cuddly assassin", imageRes = R.drawable.anh3),
            Cat(name = "Nocal", description = "Award-winning investigator", imageRes = R.drawable.anh4)
        )
    }

    val availableNames = remember { mutableStateListOf("Luna", "Simba", "Shadow", "Susu", "Xuly", "Milo", "Oscar", "Xam", "Oxi", "Bim") }
    val availableImages = remember {
        mutableStateListOf(
            R.drawable.anh2, R.drawable.anh5, R.drawable.anh6,
            R.drawable.anh10, R.drawable.anh11, R.drawable.anh12,
            R.drawable.anh7, R.drawable.anh8, R.drawable.anh9, R.drawable.anh13
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("🐾 Thêm & Xóa Mèo") },
                backgroundColor = Color.White,
                elevation = 4.dp,
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color(0xFF9C27B0))
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFCE4EC)) // MÀU NỀN CHUNG
                .padding(padding)
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = {
                    if (availableNames.isNotEmpty() && availableImages.isNotEmpty()) {
                        val randomName = availableNames.random()
                        val randomImage = availableImages.random()
                        availableNames.remove(randomName)
                        availableImages.remove(randomImage)
                        cats.add(Cat(name = randomName, description = "Secret agent in disguise", imageRes = randomImage))
                        Toast.makeText(context, "Đã thêm mèo mới: $randomName", Toast.LENGTH_SHORT).show()
                    } else Toast.makeText(context, "Hết mèo để thêm rồi!", Toast.LENGTH_SHORT).show()
                },
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6A1B9A)),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("➕ Thêm Mèo", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            }
            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(

            ) {
                items(cats, key = { it.id }) { cat ->
                    val dismissState = rememberDismissState(confirmStateChange = { value ->
                        if (value == DismissValue.DismissedToEnd || value == DismissValue.DismissedToStart) {
                            cats.remove(cat)
                            availableNames.add(cat.name)
                            availableImages.add(cat.imageRes)
                            Toast.makeText(context, "Đã xoá ${cat.name}", Toast.LENGTH_SHORT).show()
                        }
                        true
                    })


                    SwipeToDismiss(
                        state = dismissState,
                        directions = setOf(DismissDirection.StartToEnd),
                        background = {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color.Red)
                                    .padding(horizontal = 20.dp),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Delete",
                                    tint = Color.White
                                )
                            }
                        },
                        dismissContent = { CatItem(cat) }
                    )
                }
            }
        }
    }
}

@Composable
fun CatItem(cat: Cat) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF8BBD0))
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Image(
            painter = painterResource(id = cat.imageRes),
            contentDescription = cat.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(90.dp)
                .clip(RoundedCornerShape(12.dp))
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = cat.name,
                style = androidx.compose.material3.MaterialTheme.typography.titleLarge,
                color = Color.Black
            )
            Text(
                text = cat.description,
                style = androidx.compose.material3.MaterialTheme.typography.bodyMedium,
                color = Color.DarkGray
            )
        }
    }
    HorizontalDivider(thickness = 1.dp, color = Color.Gray.copy(alpha = 0.2f))
}

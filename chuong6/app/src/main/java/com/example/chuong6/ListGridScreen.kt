package com.example.chuong6.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.chuong6.R

@Composable
fun ListGridScreen(navController: NavController) {
    val leftImages = listOf(
        R.drawable.anh1, R.drawable.anh3, R.drawable.anh5, R.drawable.anh7, R.drawable.anh9
    )
    val rightImages = listOf(
        R.drawable.anh2, R.drawable.anh4, R.drawable.anh6, R.drawable.anh8, R.drawable.anh10
    )

    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(8.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(end = 4.dp)
        ) {
            items(leftImages, key = { it }) { imgId ->
                GridItem(imageId = imgId, text = "Mèo chẵn")
            }
        }
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(start = 4.dp)
        ) {
            items(rightImages, key = { it }) { imgId ->
                GridItem(imageId = imgId, text = "Mèo lẻ")
            }
        }
    }
}

@Composable
fun GridItem(imageId: Int, text: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = imageId),
            contentDescription = text,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = text, fontWeight = FontWeight.Bold, fontSize = 16.sp)
    }
}

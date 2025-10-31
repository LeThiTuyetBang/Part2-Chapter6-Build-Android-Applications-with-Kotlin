package com.example.chuong6.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.chuong6.R

@Composable
fun LazyColumnScreen(navController: NavController) {
    val imageIds = listOf(
        R.drawable.anh1, R.drawable.anh2, R.drawable.anh3, R.drawable.anh4, R.drawable.anh5,
        R.drawable.anh6, R.drawable.anh7, R.drawable.anh8, R.drawable.anh9, R.drawable.anh10
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray.copy(alpha = 0.3f))
    ) {
        items(imageIds, key = { it }) { imageId ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = imageId),
                    contentDescription = "Image $imageId",
                    modifier = Modifier
                        .height(200.dp)
                        .fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text("Ảnh mèo số: ${imageIds.indexOf(imageId) + 1}")
                Spacer(modifier = Modifier.height(16.dp))
                Divider()
            }
        }
    }
}

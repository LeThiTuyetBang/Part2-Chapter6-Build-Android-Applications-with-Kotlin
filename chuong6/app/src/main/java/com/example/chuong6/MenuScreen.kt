package com.example.chuong6.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination

// ------------------ Menu ------------------
@Composable
fun MenuScreen(navController: NavController) {
    val menuItems = listOf(
        "✨ LazyColumn" to "lazycolumn",
        "🎠 LazyRow" to "lazyrow",
        "🐱 Thêm & Xóa Mèo" to "add_remove",
        "🖼️ Danh sách & Lưới ảnh" to "list_grid"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(Color(0xFFFFE4E1), Color(0xFFFFF0F5))))
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("🐾 Thế giới Mèo xinh", fontSize = 30.sp, fontWeight = FontWeight.Bold, color = Color(0xFFD81B60))
        Spacer(modifier = Modifier.height(20.dp))
        menuItems.forEach { (title, route) ->
            Button(
                onClick = {
                    navController.navigate(route) {
                        popUpTo(navController.graph.findStartDestination().id)
                        launchSingleTop = true
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                shape = RoundedCornerShape(50)
            ) {
                Text(title, fontSize = 18.sp)
            }
        }
    }
}

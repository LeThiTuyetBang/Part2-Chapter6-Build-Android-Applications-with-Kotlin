package com.example.chuong6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import java.util.UUID
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

data class Cat(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val description: String,
    val imageRes: Int
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                CatWorldApp()
            }
        }
    }
}

@Composable
fun CatWorldApp() {
    var showWelcome by remember { mutableStateOf(true) }
    if (showWelcome) WelcomeScreen(onStartClick = { showWelcome = false })
    else NavigationApp()
}

// ------------------ Welcome ------------------
@Composable
fun WelcomeScreen(onStartClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(listOf(Color(0xFFFFC1CC), Color(0xFFFFE4E1), Color(0xFFFFC0CB)))
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("🐾 Xin chào!", color = Color(0xFF880E4F), fontSize = 36.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Chào mừng bạn đến với\nThế giới Mèo 🐱",
                color = Color(0xFFD81B60),
                fontSize = 26.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(32.dp))
            Button(onClick = onStartClick) { Text("Bắt đầu ngay 💖", fontSize = 20.sp) }
        }
    }
}

// ------------------ Navigation ------------------
@Composable
fun NavigationApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "menu") {
        composable("menu") { MenuScreen(navController) }
        composable("lazycolumn") { LazyColumnScreen(navController) }
        composable("lazyrow") { LazyRowScreen(navController) }
        composable("add_remove") { CatAppScreen(navController) }
        composable("list_grid") { ListGridScreen(navController) }
    }
}

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

// ------------------ LazyColumn ------------------
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LazyColumnScreen(navController: NavController) {
    androidx.compose.material3.Scaffold(
        topBar = {
            androidx.compose.material3.TopAppBar(
                title = { Text("✨ LazyColumn") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color(0xFFD81B60)
                        )
                    }
                },
                // Đặt màu nền cho TopAppBar
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color(0xFFFFF0F5)),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(10) { i ->
                // Dùng Card của Material 3
                androidx.compose.material3.Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(4.dp, RoundedCornerShape(12.dp)),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(
                        // Màu nền hồng kem cho mỗi item, rất hài hòa
                        containerColor = Color(0xFFFFF8F9)
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            "🌟 Item #$i",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFAD1457)
                        )
                    }
                }
            }
        }
    }
}

// ------------------ Danh sách & Lưới ảnh ------------------
@Composable
fun ListGridScreen(navController: NavController) {
    val images = listOf(
        R.drawable.anh1, R.drawable.anh2, R.drawable.anh3, R.drawable.anh4,
        R.drawable.anh5, R.drawable.anh6, R.drawable.anh7, R.drawable.anh8,
        R.drawable.anh9, R.drawable.anh10, R.drawable.anh11, R.drawable.anh12,
        R.drawable.anh13
    )

    val leftImages = images.filterIndexed { index, _ -> index % 2 == 0 }
    val rightImages = images.filterIndexed { index, _ -> index % 2 == 1 }

    Scaffold(topBar = {
        TopAppBar(
            title = { Text("🖼️ Bộ sưu tập ảnh dạng Lưới") },
            navigationIcon = {
                androidx.compose.material.IconButton(onClick = { navController.popBackStack() }) {
                    androidx.compose.material.Icon(painter = painterResource(id = R.drawable.back), contentDescription = "Back", tint = Color(0xFF42A5F5))
                }
            }
        )
    }) { padding ->
        Row(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color(0xFFFFF5F5))
                .padding(6.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            LazyColumn(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                items(leftImages) { img ->
                    Image(
                        painter = painterResource(id = img), contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp))
                    )
                }
            }
            LazyColumn(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                items(rightImages) { img ->
                    Image(
                        painter = painterResource(id = img), contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp))
                    )
                }
            }
        }
    }
}



package com.example.bookstore.main_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookstore.R
import com.example.bookstore.ui.theme.DarkBrown
import com.example.bookstore.ui.theme.GreyLight

@Composable
fun DriverBody() {
    val categoriesList = listOf(
        "Favorites",
        "Fantasy",
        "Drama",
        "BestSellers"
    )
    Box(modifier = Modifier.fillMaxSize().background(DarkBrown)){
        Image(
            painter = painterResource(id = R.drawable.book_tree),
            modifier = Modifier.fillMaxSize(),
            contentDescription = "",
            alpha = 0.2f,
            contentScale = ContentScale.Crop
            )
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Categories",
                fontSize = 20.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(10.dp))
            Box(
                modifier = Modifier.fillMaxWidth()
                    .height(1.dp)
                    .background(GreyLight)
            )
            LazyColumn(Modifier.fillMaxSize()) {
                items(categoriesList) { item ->
                    Column(Modifier
                        .fillMaxWidth()
                        .clickable {  }
                    ) {
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(text = item,
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentWidth()
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Box(
                            modifier = Modifier.fillMaxWidth()
                                .height(1.dp)
                                .background(GreyLight)
                        )
                    }
                }
            }
        }
    }
}

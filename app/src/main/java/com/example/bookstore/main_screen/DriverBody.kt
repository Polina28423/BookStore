package com.example.bookstore.main_screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookstore.R
import com.example.bookstore.ui.theme.ButtonColor
import com.example.bookstore.ui.theme.DarkBrown
import com.example.bookstore.ui.theme.GreyLight
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

@Composable
fun DriverBody() {
    val categoriesList = listOf(
        "Favorites",
        "Fantasy",
        "Drama",
        "BestSellers"
    )
    val isAdminState = remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        isAdmin { isAdmin ->
            isAdminState.value = isAdmin
        }
    }
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
            LazyColumn(Modifier.fillMaxWidth()) {
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
            if(isAdminState.value) Button(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = ButtonColor
                )
            ) {
                Text(text = "Admin panel")
            }
        }
    }

}

fun isAdmin(onAdmin: (Boolean) -> Unit){
    val uid = Firebase.auth.currentUser!!.uid
    val db = Firebase.firestore.collection("admins")
        .document(uid).get().addOnSuccessListener{
            onAdmin(it.get("isAdmin") as Boolean)
        }
}


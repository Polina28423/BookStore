package com.example.bookstore.add_book_screen

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter
import com.example.bookstore.R
import com.example.bookstore.login.LoginButton
import com.example.bookstore.login.RoundedCornerTextField
import com.example.bookstore.ui.theme.BoxFilterColor

@Composable
fun AddBookScreen() {
    val title = remember {
        mutableStateOf("")
    }
    val description = remember {
        mutableStateOf("")
    }
    val price = remember {
        mutableStateOf("")
    }
    val selectedImageUri = remember {
        mutableStateOf<Uri?>(null)
    }

    val imageLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ){
        uri ->
        selectedImageUri.value = uri
    }

    Image(
        painter = rememberAsyncImagePainter(
            model = selectedImageUri.value),
        contentDescription = "BG",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop,
        alpha = 0.4f
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BoxFilterColor)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = 40.dp, end = 40.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_book),
            contentDescription = "Logo",
            modifier = Modifier.size(90.dp)

        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Add new book",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
            fontFamily = FontFamily.Serif
        )
        RoundedCornerTextField(
            text = title.value,
            label = "Title"
        ) {
            title.value = it
        }
        Spacer(modifier = Modifier.height(10.dp))
        RoundedCornerTextField(
            maxLines = 5,
            singleLine = false,
            text = description.value,
            label = "Description"
        ) {
            description.value = it
        }
        Spacer(modifier = Modifier.height(10.dp))
        RoundedCornerTextField(
            text = price.value,
            label = "Price"
        ) {
            price.value = it
        }
        Spacer(modifier = Modifier.height(10.dp))

        LoginButton(text = "Select image") {
            imageLauncher.launch("image/*")
        }
    }
    LoginButton(text = "Save") {

    }

}
package com.example.bookstore.login

import android.util.Log
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookstore.R
import com.example.bookstore.ui.theme.BoxFilterColor
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

@Composable
fun LoginScreen() {
    val auth = remember {

        Firebase.auth
    }
    val errorState = remember {
        mutableStateOf("")
    }
    val emailState = remember {
        mutableStateOf("")
    }
    val passwordState = remember {
        mutableStateOf("")
    }

    Image(
        painter = painterResource(
            id = R.drawable.book_tree
        ),
        contentDescription = "BG",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
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
            modifier = Modifier.size(150.dp)

        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "UKR Book Store",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            fontFamily = FontFamily.Serif
        )
        RoundedCornerTextField(
            text = emailState.value,
            label = "Email"
        ) {
            emailState.value = it
        }
        Spacer(modifier = Modifier.height(10.dp))
        RoundedCornerTextField(
            text = passwordState.value,
            label = "Password"
        ) {
            passwordState.value = it
        }
        Spacer(modifier = Modifier.height(10.dp))
        if (errorState.value.isNotEmpty()) {
            Text(
                text = errorState.value,
                color = Color.Red,
                textAlign = TextAlign.Center
            )
        }
        LoginButton(text = "Sign In") {
            singIn(
                auth,
                emailState.value,
                passwordState.value,
                onSignInSuccess = {
                    Log.d("MyLog", "Sign In Success")
                },
                onSignInFailure = { error ->
                    errorState.value = error
                }
            )
        }
    }
    LoginButton(text = "Sign Up") {
        singUp(
            auth,
            emailState.value,
            passwordState.value,
            onSignUpSuccess = {
                Log.d("MyLog", "Sign Up Success")
            },
            onSignUpFailure = { error ->
                errorState.value = error
            }
        )
    }

}


fun singUp(
    auth: FirebaseAuth,
    email: String,
    password: String,
    onSignUpSuccess: () -> Unit,
    onSignUpFailure: (String) -> Unit
) {
    if (email.isBlank() || password.isBlank()) {
        onSignUpFailure("Email and password cannot be empty")
        return
    }

    auth.createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) onSignUpSuccess()
        }
        .addOnFailureListener {
            onSignUpFailure(it.message ?: "Sign Up Error")
        }
}

fun singIn(
    auth: FirebaseAuth,
    email: String,
    password: String,
    onSignInSuccess: () -> Unit,
    onSignInFailure: (String) -> Unit
) {
    if (email.isBlank() || password.isBlank()) {
        onSignInFailure("Email and password cannot be empty")
        return
    }

    auth.signInWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) onSignInSuccess()
        }
        .addOnFailureListener {
            onSignInFailure(it.message ?: "Sign In Error")
        }
}
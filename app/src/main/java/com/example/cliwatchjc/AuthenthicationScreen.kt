package com.example.cliwatchjc

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cliwatchjc.data.UserViewModel
import kotlinx.coroutines.delay

@Composable
fun MainAuthenticationScreen(
    navController: NavController,
    viewModel: UserViewModel
) {
    var showWelcomeScreen by remember { mutableStateOf(true) }

    if (showWelcomeScreen) {
        WelcomeScreen(onSwiped = { showWelcomeScreen = false })
    } else {
        AuthenticationScreen(navController,viewModel)
    }
}

@Composable
fun WelcomeScreen(onSwiped: () -> Unit) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .detectHorizontalSwipe(onSwipedLeft = onSwiped),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .padding(20.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(24.dp)
            ) {
                Text(
                    text = "Welcome to CliWatch!",
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Empower your journey towards a greener future. Explore climate education, stay updated with news, track your carbon footprint, and take on challenges to make a difference.",
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp
                )
            }
        }
    }
}


@SuppressLint("ModifierFactoryUnreferencedReceiver")
@OptIn(ExperimentalComposeUiApi::class)
fun Modifier.detectHorizontalSwipe(onSwipedLeft: () -> Unit): Modifier {
    return pointerInput(Unit) {
        detectTransformGestures { _, pan, _, _ ->
            if (pan.x < 0) { // Negative indicates a swipe to the left
                onSwipedLeft()
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AuthenticationScreen(
    navController: NavController,
    viewModel: UserViewModel
) {
    var isRegistering by remember { mutableStateOf(true) }
    var userName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val feedback by viewModel.feedback.collectAsState()
    val keyboardController = LocalSoftwareKeyboardController.current
    val isAuthenticated by viewModel.isAuthenticated.collectAsState()

    // Navigate when the user is authenticated
    LaunchedEffect(isAuthenticated) {
        if (isAuthenticated) {
            delay(2000)
            navController.navigate(Routes.MAIN_MENU) {
                popUpTo(Routes.WELCOME) {
                    inclusive = true // This should remove the Welcome route from the stack
                }
                launchSingleTop = true
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .padding(20.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(16.dp)
            ) {
                // Username field
                TextField(
                    value = userName,
                    onValueChange = { userName = it },
                    label = { Text("Username") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Password field
                TextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Button which will change its functionality and text based on isRegistering
                Button(onClick = {
                    if (isRegistering) {
                        viewModel.registerUser(userName, password)
                    } else {
                        viewModel.loginUser(userName, password)
                    }
                    keyboardController?.hide()
                }) {
                    Text(if (isRegistering) "Register" else "Login")
                }

                // Text to toggle between Register and Login
                TextButton(onClick = { isRegistering = !isRegistering }) {
                    Text(if (isRegistering) "Already have an account? Login" else "Don't have an account? Register")
                }

                feedback?.let {
                    Snackbar(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                        Text(it)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun previewWelcomeScreen() {

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .padding(24.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(24.dp)
            ) {
                Text(
                    text = "Welcome to CliWatch!",
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Empower your journey towards a greener future. Explore climate education, stay updated with news, track your carbon footprint, and take on challenges to make a difference.",
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp
                )
            }
        }
    }
}
package com.example.cliwatchjc

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cliwatchjc.modules.education.ArticleViewModel

@Composable
fun MainMenuScreen() {
    val articleViewModel: ArticleViewModel = hiltViewModel()
    val currentUser = articleViewModel.currentUser

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column {
            // Display the user ID
            Text(text = "User ID: ${currentUser?.userId}")
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = Routes.labels[Routes.MAIN_MENU] ?: "")
        }
    }
}

class UserManager {
    // For this example, let's say the default user always has userId=1
    val currentUser: User? = User(userId = 1)

    data class User(val userId: Long)
}

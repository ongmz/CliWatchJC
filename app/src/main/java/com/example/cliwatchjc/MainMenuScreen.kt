package com.example.cliwatchjc

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cliwatchjc.data.User
import com.example.cliwatchjc.modules.education.ArticleViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@Composable
fun MainMenuScreen() {
    val articleViewModel: ArticleViewModel = hiltViewModel()
    val currentUser by articleViewModel.currentUser.collectAsState()
    val userTotalScore by articleViewModel.userTotalScore.collectAsState()
    val totalQuestions by articleViewModel.totalQuestions.collectAsState()

    BackHandler { }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Card(
                modifier = Modifier
                        .width(308.dp)
                        .height(540.dp)
                        .padding(bottom = 32.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
        ) {
            Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                        painter = painterResource(id = R.drawable.img_user),
                        contentDescription = "",
                        modifier = Modifier
                                .clip(CircleShape)
                )
                Divider(color = Color.Gray, thickness = 4.dp, modifier = Modifier.padding(vertical = 16.dp, horizontal = 4.dp))
                Text(text = "${currentUser?.userName} (ID: ${currentUser?.userId})", fontSize = 20.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
                Row {
                    Text(text = "Quiz Scores: $userTotalScore/$totalQuestions")
                }
            }
        }
    }
}

class UserManager {
    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> get() = _currentUser.asStateFlow()

    fun setUser(user: User) {
        _currentUser.value = user
    }
}




package com.example.cliwatchjc.modules.challenges

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
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.cliwatchjc.R
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cliwatchjc.Routes
@Composable
fun ChallengesScreen(navController: NavController) {
    val challengeViewModel: ChallengesViewModel = hiltViewModel()
    LaunchedEffect(Unit){
        challengeViewModel.populateChallengeEntity()
    }
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp)
    ){
        Spacer(modifier = Modifier.height(80.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
                .clickable { navController.navigate(Routes.CHALLENGES_LIST) },
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
        ){
            Box(modifier = Modifier.fillMaxSize()){
                Image(
                    painter = painterResource(id = R.drawable.add_icon),
                    contentDescription = "Education Resources Icon",
                    modifier = Modifier
                        .padding(top = 36.dp)
                        .align(Alignment.TopCenter)
                )
                Text(
                    text = Routes.labels[Routes.CHALLENGES_LIST] ?: "",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF313331),
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 12.dp)
                )
            }
        }
        Spacer(modifier = Modifier
            .height(20.dp)
            .background(Color.Gray))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
                .clickable { navController.navigate(Routes.PROGRESS) },
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.progress_icon),
                    contentDescription = "Climate News Icon",
                    modifier = Modifier
                        .size(220.dp)
                        .padding(top = 48.dp)
                        .align(Alignment.TopCenter)
                )
                Text(
                    text = Routes.labels[Routes.PROGRESS] ?: "",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF313331),
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 12.dp)
                )
            }
        }
    }
}

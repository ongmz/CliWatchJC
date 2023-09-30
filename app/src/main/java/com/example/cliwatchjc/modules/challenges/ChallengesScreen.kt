package com.example.cliwatchjc.modules.challenges

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cliwatchjc.Routes
import com.example.compose.AppTheme
import com.example.cliwatchjc.R


@Composable
fun ChallengesScreen(navController: NavController, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(80.dp))
        }

        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clickable { navController.navigate(Routes.CHALLENGES_LIST) },
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.add_icon),
                        contentDescription = "Add Challenges Icon",
                        modifier = Modifier
                            .size(120.dp)
                            .padding(top = 16.dp)
                            .align(Alignment.TopCenter)
                    )
                    Text(
                        text = Routes.labels[Routes.CHALLENGES_LIST] ?: "",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF313331),
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(bottom = 12.dp)
                    )
                }
            }
        }

        item {
            Spacer(modifier = Modifier
                .height(20.dp)
                .background(Color.Gray))
        }

        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clickable { navController.navigate(Routes.PROGRESS) },
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.progress_icon),
                        contentDescription = "Personal Goal Icon",
                        modifier = Modifier
                            .size(120.dp)
                            .padding(top = 16.dp)
                            .align(Alignment.TopCenter)
                    )
                    Text(
                        text = Routes.labels[Routes.PROGRESS] ?: "",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF313331),
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(bottom = 12.dp)
                    )
                }
            }
        }

        item {
            Spacer(modifier = Modifier
                .height(20.dp)
                .background(Color.Gray))
        }

        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clickable { navController.navigate(Routes.LEADERBOARD) },
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.leaderboard_icon),
                        contentDescription = "Leaderboard Icon",
                        modifier = Modifier
                            .size(120.dp)
                            .padding(top = 16.dp)
                            .align(Alignment.TopCenter)
                    )
                    Text(
                        text = Routes.labels[Routes.LEADERBOARD] ?: "",
                        fontSize = 20.sp,
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
}


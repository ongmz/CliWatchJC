package com.example.cliwatchjc.modules.tracker

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cliwatchjc.R
import com.example.cliwatchjc.Routes
import com.example.compose.AppTheme
import androidx.compose.foundation.lazy.LazyColumn

@Composable
fun TrackerScreen(navController: NavController) {
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
                    .height(280.dp)
                    .clickable { navController.navigate(Routes.CALCULATOR) },
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_calculator),
                        contentDescription = "Calculator Icon",
                        modifier = Modifier
                            .size(220.dp)
                            .padding(top = 48.dp)
                            .align(Alignment.TopCenter)
                    )
                    Text(
                        text = Routes.labels[Routes.CALCULATOR] ?: "",
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

        item {
            Spacer(modifier = Modifier.height(20.dp).background(Color.Gray))
        }

        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
                    .clickable { navController.navigate(Routes.PERSONAL_GOAL) },
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_personal_goals),
                        contentDescription = "Personal Goal Icon",
                        modifier = Modifier
                            .size(220.dp)
                            .padding(top = 48.dp)
                            .align(Alignment.TopCenter)
                    )
                    Text(
                        text = Routes.labels[Routes.PERSONAL_GOAL] ?: "",
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

        item {
            Spacer(modifier = Modifier.height(20.dp).background(Color.Gray))
        }

        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
                    .clickable { navController.navigate(Routes.SUMMARY) },
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_summary),
                        contentDescription = "Summary Icon",
                        modifier = Modifier
                            .size(220.dp)
                            .padding(top = 48.dp)
                            .align(Alignment.TopCenter)
                    )
                    Text(
                        text = Routes.labels[Routes.SUMMARY] ?: "",
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
        item { Spacer(modifier = Modifier.height(96.dp).background(Color.Gray)) }
    }
}

@Preview(showBackground = true)
@Composable
fun TrackerScreenPreview() {
    AppTheme {
        val navController = rememberNavController()
        TrackerScreen(navController)
    }
}


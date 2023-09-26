package com.example.cliwatchjc.modules.education

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cliwatchjc.R
import com.example.cliwatchjc.Routes
import com.example.compose.AppTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun EducationScreen(navController: NavController, modifier: Modifier = Modifier) {

    val articleViewModel: ArticleViewModel = hiltViewModel()
    LaunchedEffect(Unit) {
        articleViewModel.populateEducationEntity()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(80.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
                .clickable { navController.navigate(Routes.EDUCATION_RESOURCES) },
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Image(
                    painter = painterResource(id = R.drawable.ic_education_resource),
                    contentDescription = "Education Resources Icon",
                    modifier = Modifier
                        .padding(top = 36.dp)
                        .align(Alignment.TopCenter)
                )
                Text(
                    text = Routes.labels[Routes.EDUCATION_RESOURCES] ?: "",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF313331),
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 12.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp).background(Color.Gray))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
                .clickable { navController.navigate(Routes.CLIMATE_NEWS) },
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_climate_news),
                    contentDescription = "Climate News Icon",
                    modifier = Modifier
                        .size(220.dp)
                        .padding(top = 48.dp)
                        .align(Alignment.TopCenter)
                )
                Text(
                    text = Routes.labels[Routes.CLIMATE_NEWS] ?: "",
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

@Preview(showBackground = true)
@Composable
fun EducationScreenPreview() {
    AppTheme {
        val navController = rememberNavController()
        EducationScreen(navController)
    }
}
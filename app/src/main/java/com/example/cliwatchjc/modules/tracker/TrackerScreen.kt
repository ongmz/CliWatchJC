package com.example.cliwatchjc.modules.tracker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cliwatchjc.Routes
import com.example.compose.AppTheme

@Composable
fun TrackerScreen(navController: NavController, modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, top = 16.dp)
    ) { Text(text = Routes.labels[Routes.TRACKER] ?: "")

        Spacer(modifier.height(16.dp))

        Button(onClick = {
            navController.navigate(Routes.CALCULATOR)
        }) {
            Text(text = Routes.labels[Routes.CALCULATOR] ?: "")
        }

        Button(onClick = {
            navController.navigate(Routes.PERSONAL_GOAL)
        }) {
            Text(text = Routes.labels[Routes.PERSONAL_GOAL] ?: "")
        }

        Button(onClick = {
            navController.navigate(Routes.SUMMARY)
        }) {
            Text(text = Routes.labels[Routes.SUMMARY] ?: "")
        }

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
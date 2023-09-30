package com.example.cliwatchjc.modules.tracker

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.cliwatchjc.Routes

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun CalculatorResultScreen(navController: NavController, result: Float) {
    val scrollState = rememberScrollState()

    val backStackEntry by navController.currentBackStackEntryAsState()
    val arguments = backStackEntry?.arguments

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 28.dp, end = 28.dp)
        ) {
            Spacer(modifier = Modifier.height(80.dp))

            Text(
                text = "Carbon Footprint:",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                text = "$result CO2e tons",
                style = TextStyle(
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Tips Section
            Text(
                text = "Tips to Reduce Your Carbon Footprint:",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )

            // LazyColumn for displaying tips
            LazyColumn {
                items(tipsList) { tip ->
                    Tip(tip)
                }
            }

            Button(
                onClick = {
                    // Navigate back to the CalculatorScreen
                    navController.navigate(Routes.CALCULATOR)
                }
            ) {
                Text("Back")
            }
        }
    }
}

@Composable
fun Tip(text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(4.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Info,
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text)
    }
}

val tipsList = listOf(
    "Consume local and seasonal products",
    "Try the train for your next holiday",
    "Limit and recycle your waste"
)
package com.example.cliwatchjc.modules.tracker

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun CalculatorResultScreen(result: Float) {
    val calculatorViewModel: CalculatorViewModel = hiltViewModel()
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        // Display Carbon Footprint with larger text
        val carbonFootprint = calculatorViewModel.carbonFootprint.value
        Text(
            text = "Carbon Footprint:",
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Text(
            text = "$carbonFootprint CO2e tons",
            style = TextStyle(
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold
            )
        )

        // Add some spacing between the result and tips
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
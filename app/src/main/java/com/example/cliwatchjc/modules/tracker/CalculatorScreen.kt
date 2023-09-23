package com.example.cliwatchjc.modules.tracker

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CalculatorScreen() {
    var transportation: Float by remember { mutableFloatStateOf(0.0f) }
    var energyUsage: Float by remember { mutableFloatStateOf(0.0f) }
    var waste: Float by remember { mutableFloatStateOf(0.0f) }
    var carbonFootprint: Float by remember { mutableFloatStateOf(0.0f) }
    var showResult by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        // Title
        Text(
            text = "Calculate Your Carbon Footprint",
            style = androidx.compose.ui.text.TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(bottom = 16.dp)
        )
        // Transportation Input
        Text("Transportation (in CO2e tons)")
        Slider(value = transportation, onValueChange = { transportation = it })

        // Energy Usage Input
        Text("Energy Usage (in CO2e tons)")
        Slider(value = energyUsage, onValueChange = { energyUsage = it })

        // Waste Input
        Text("Waste (in CO2e tons)")
        Slider(value = waste, onValueChange = { waste = it })

        // Calculate Button
        Button(
            onClick = {
                // Calculate the carbon footprint
                carbonFootprint = transportation + energyUsage + waste
                showResult = true // Show the result
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Calculate Carbon Footprint")
        }

        // Display Carbon Footprint if showResult is true
        if (showResult) {
            Text("Carbon Footprint: $carbonFootprint CO2e tons")
            Button(
                onClick = {
                    showResult = false // Hide the result
                },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Back to Calculator")
            }
        }
    }
}

@Preview
@Composable
fun CalculatorPreview() {
    CalculatorScreen()
}



package com.example.cliwatchjc.modules.tracker

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.TextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun CalculatorScreen(calculatorViewModel: CalculatorViewModel) {
    var transportation by remember { mutableStateOf("") }
    var energyUsage by remember { mutableStateOf("") }
    var waste by remember { mutableStateOf("") }
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
        TextField(
            value = transportation,
            onValueChange = { transportation = it },
            label = { Text("Transportation (in CO2e tons)") }
        )

        // Energy Usage Input
        TextField(
            value = energyUsage,
            onValueChange = { energyUsage = it },
            label = { Text("Energy Usage (in CO2e tons)") }
        )

        // Waste Input
        TextField(
            value = waste,
            onValueChange = { waste = it },
            label = { Text("Waste (in CO2e tons)") }
        )

        // Calculate Button
        Button(
            onClick = {
                // Calculate the carbon footprint
                val transportationValue = transportation.toFloatOrNull() ?: 0.0f
                val energyUsageValue = energyUsage.toFloatOrNull() ?: 0.0f
                val wasteValue = waste.toFloatOrNull() ?: 0.0f
                calculatorViewModel.setTransportation(transportationValue)
                calculatorViewModel.setEnergyUsage(energyUsageValue)
                calculatorViewModel.setWaste(wasteValue)
                calculatorViewModel.calculateCarbonFootprint()
                showResult = true // Show the result
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Calculate Carbon Footprint")
        }

        // Display Carbon Footprint if showResult is true
        if (showResult) {
            val carbonFootprint = calculatorViewModel.carbonFootprint.value
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
    CalculatorScreen(calculatorViewModel = CalculatorViewModel())
}

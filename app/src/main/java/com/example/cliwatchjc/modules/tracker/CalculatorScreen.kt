package com.example.cliwatchjc.modules.tracker

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cliwatchjc.Routes

@Composable
fun CalculatorScreen() {
    var transportation: Float by remember { mutableFloatStateOf(0.0f) }
    var energyUsage: Float by remember { mutableFloatStateOf(0.0f) }
    var waste: Float by remember { mutableFloatStateOf(0.0f) }
    var carbonFootprint: Float by remember { mutableFloatStateOf(0.0f) }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
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
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Calculate Carbon Footprint")
        }

        // Display Carbon Footprint
        Text("Carbon Footprint: $carbonFootprint CO2e tons")
    }
}

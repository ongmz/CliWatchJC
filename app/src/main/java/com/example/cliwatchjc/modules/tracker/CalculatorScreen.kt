package com.example.cliwatchjc.modules.tracker

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculatorScreen(calculatorViewModel: CalculatorViewModel) {
    val transportationOptions = listOf("Car", "Motorcycle", "Public Transports")
    var selectedTransportation by remember { mutableStateOf(transportationOptions[0]) }
    var transportationDistance by remember { mutableStateOf("") }
    val dietOptions = listOf("Vegan", "Vegetarian", "Meat Lover")
    var selectedDiet by remember { mutableStateOf(dietOptions[0]) }
    var foodSpending by remember { mutableStateOf("") }
    var electricityUsage by remember { mutableStateOf("") }
    var naturalGasUsage by remember { mutableStateOf("") }
    var firewoodUsage by remember { mutableStateOf("") }
    var showResult by remember { mutableStateOf(false) }
    var transportationMenuExpanded by remember { mutableStateOf(false) }
    var dietMenuExpanded by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier
            .padding(start = 28.dp, end = 28.dp)
            .fillMaxWidth()
    ) {
        item {
            Spacer(modifier = Modifier.height(40.dp))
        }

        item {
            // Title
            Text(
                text = "Carbon Footprint Calculator",
                style = TextStyle(
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                ),

                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Transportation Input
            Text(
                text = "Transportation",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                )
            )
            ExposedDropdownMenuBox(
                expanded = transportationMenuExpanded,
                onExpandedChange = { transportationMenuExpanded = !transportationMenuExpanded },
            ) {
                TextField(
                    modifier = Modifier.menuAnchor(),
                    readOnly = true,
                    value = selectedTransportation,
                    onValueChange = {},
                    label = { Text("Mode") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = transportationMenuExpanded)
                    },
                    colors = ExposedDropdownMenuDefaults.textFieldColors(),
                )
                ExposedDropdownMenu(
                    expanded = transportationMenuExpanded,
                    onDismissRequest = { transportationMenuExpanded = false }
                ) {
                    transportationOptions.forEach { selectionOption ->
                        DropdownMenuItem(text = { Text(selectionOption) }, onClick = {
                            selectedTransportation = selectionOption
                            transportationMenuExpanded = false
                        })
                    }
                }
            }
            TextField(
                value = transportationDistance,
                onValueChange = { transportationDistance = it },
                label = { Text("Distance Travelled (km/week)") }
            )

            // Food Input
            Text(
                text = "Food",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                )
            )
            ExposedDropdownMenuBox(
                expanded = dietMenuExpanded,
                onExpandedChange = { dietMenuExpanded = !dietMenuExpanded },
            ) {
                TextField(
                    modifier = Modifier.menuAnchor(),
                    readOnly = true,
                    value = selectedDiet,
                    onValueChange = {},
                    label = { Text("Diet Type") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = dietMenuExpanded)
                    },
                    colors = ExposedDropdownMenuDefaults.textFieldColors(),
                )
                ExposedDropdownMenu(
                    expanded = dietMenuExpanded,
                    onDismissRequest = { dietMenuExpanded = false }
                ) {
                    dietOptions.forEach { selectionOption ->
                        DropdownMenuItem(text = { Text(selectionOption) }, onClick = {
                            selectedDiet = selectionOption
                            dietMenuExpanded = false
                        })
                    }
                }
            }
            TextField(
                value = foodSpending,
                onValueChange = { foodSpending = it },
                label = { Text("Food Spending (\$/week)") }
            )

            // Energy Input
            Text(
                text = "Energy",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                )
            )
            TextField(
                value = electricityUsage,
                onValueChange = { electricityUsage = it },
                label = { Text("Electricity Usage (kWh)") }
            )

            TextField(
                value = naturalGasUsage,
                onValueChange = { naturalGasUsage = it },
                label = { Text("Natural Gas Usage (MJ)") }
            )

            TextField(
                value = firewoodUsage,
                onValueChange = { firewoodUsage = it },
                label = { Text("Firewood Usage (kg)") }
            )

            // Calculate Button
            Button(
                onClick = {
                    val transportationValue = calculatorViewModel.calculateTransportation(
                        selectedTransportation,
                        transportationDistance
                    )
                    val foodValue =
                        calculatorViewModel.calculateFood(selectedDiet, foodSpending)
                    val energyValue = calculatorViewModel.calculateEnergy(
                        electricityUsage,
                        naturalGasUsage,
                        firewoodUsage
                    )
                    val totalCarbonFootprint = transportationValue + foodValue + energyValue
                    calculatorViewModel.setCarbonFootprint(totalCarbonFootprint)
                    showResult = true
                },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Calculate")
            }

            // Display Result and Tips
            if (showResult) {
                ResultAndTipsSection(calculatorViewModel)
            }
        }
    }
}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun ResultAndTipsSection(calculatorViewModel: CalculatorViewModel) {
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


@Preview
@Composable
fun CalculatorPreview() {
    CalculatorScreen(calculatorViewModel = CalculatorViewModel())
}


package com.example.cliwatchjc.modules.tracker

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculatorScreen(calculatorViewModel: CalculatorViewModel, navController: NavHostController) {
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
    var result by remember { mutableFloatStateOf(0.0f) }
    val scrollState = rememberScrollState()


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 28.dp, end = 28.dp)
            .verticalScroll(state = scrollState)

    ) {
        Spacer(modifier = Modifier.height(80.dp))
        // Title
        Text(
            text = "Carbon Footprint Calculator",
            style = TextStyle(
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            ),

            modifier = Modifier.padding(bottom = 20.dp)
        )

        // Transportation Input
        Text(
            text = "Transportation",
            style = TextStyle(
                fontSize = 28.sp,
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
                fontSize = 28.sp,
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
                fontSize = 28.sp,
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
                result = transportationValue + foodValue + energyValue // Calculate the result here
                calculatorViewModel.setCarbonFootprint(result)
                showResult = true
                // Navigate to the result screen and pass the result value
                calculatorViewModel.navigateToResultScreen(navController)
            },
            modifier = Modifier.padding(top = 20.dp)
        ) {
            Text("Calculate")
        }

        Spacer(modifier = Modifier.height(96.dp))

        // Observe navigation state and navigate if there's a destination
        val calculatorResultNavigation by calculatorViewModel.calculatorResultNavigation.collectAsState()
        calculatorResultNavigation?.let { destination ->
            when (destination) {
                is CalculatorViewModel.CalculatorScreenNavigation.CalculatorResult -> {
                    // Calculate the result here based on your logic
                    CalculatorResultScreen(result)
                }
            }
        }
    }
}


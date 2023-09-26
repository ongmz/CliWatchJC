package com.example.cliwatchjc.modules.tracker

import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun SummaryScreen() {
    val calculatorViewModel: CalculatorViewModel = hiltViewModel()
    val sampleWeeklyTransportationData =
        calculatorViewModel.generateSampleWeeklyTransportationData()
    val sampleWeeklyEnergyUsageData =
        calculatorViewModel.generateSampleWeeklyEnergyUsageData()
    val sampleWeeklyWasteData =
        calculatorViewModel.generateSampleWeeklyWasteData()
    val sampleMonthlyTransportationData =
        calculatorViewModel.generateSampleMonthlyTransportationData()
    val sampleMonthlyEnergyUsageData =
        calculatorViewModel.generateSampleMonthlyEnergyUsageData()
    val sampleMonthlyWasteData =
        calculatorViewModel.generateSampleMonthlyWasteData()
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    val weeklyData = when (selectedTabIndex) {
        0 -> calculatorViewModel.weeklyTransportationData
        1 -> calculatorViewModel.weeklyEnergyUsageData
        2 -> calculatorViewModel.weeklyWasteData
        else -> calculatorViewModel.weeklyTransportationData
    }.collectAsState()

    val monthlyData = when (selectedTabIndex) {
        3 -> calculatorViewModel.monthlyTransportationData
        4 -> calculatorViewModel.monthlyEnergyUsageData
        5 -> calculatorViewModel.monthlyWasteData
        else -> calculatorViewModel.monthlyTransportationData
    }.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White) // Set the background color for the entire SummaryScreen
    ) {
        TabRow(
            selectedTabIndex = selectedTabIndex,
            contentColor = Color.Black
        ) {
            Tab(
                text = { Text("Weekly") },
                selected = selectedTabIndex in 0..2,
                onClick = { selectedTabIndex = 0 }
            )
            Tab(
                text = { Text("Monthly") },
                selected = selectedTabIndex in 3..5,
                onClick = { selectedTabIndex = 3 }
            )
        }

        when (selectedTabIndex) {
            in 0..2 -> {
                BarChart(
                    data = when (selectedTabIndex) {
                        0 -> sampleWeeklyTransportationData
                        1 -> sampleWeeklyEnergyUsageData
                        2 -> sampleWeeklyWasteData
                        else -> emptyList() // Default case
                    },
                    barColor = when (selectedTabIndex) {
                        0 -> Color.Blue
                        1 -> Color.Green
                        2 -> Color.Red
                        else -> Color.Blue // Default case
                    }
                )
            }

            in 3..5 -> {
                BarChart(
                    data = when (selectedTabIndex) {
                        3 -> sampleMonthlyTransportationData
                        4 -> sampleMonthlyEnergyUsageData
                        5 -> sampleMonthlyWasteData
                        else -> emptyList() // Default case
                    },
                    barColor = when (selectedTabIndex) {
                        3 -> Color.Blue //Transportation
                        4 -> Color.Green
                        5 -> Color.Red
                        else -> Color.Blue // Default case
                    }
                )
            }
        }

        // Add a button to reset the calculator inputs
        Button(
            onClick = {
                calculatorViewModel.resetInputs()
            },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Reset Calculator Inputs")
        }
    }
}

@Composable
fun BarChart(
    data: List<Float>, // Unwrap data from State using .value
    barColor: Color = Color.Blue,
    barWidth: Float = 20f
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(16.dp)
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            val maxData = data.maxOrNull() ?: 1.0f
            val barSpacing = 16.dp.toPx()
            val availableWidth = size.width - (data.size + 1) * barSpacing
            val barUnitWidth = availableWidth / data.size

            data.forEachIndexed { index, value ->
                val barHeight = (value / maxData) * size.height
                val startX = (index * (barSpacing + barUnitWidth)) + barSpacing
                val startY = size.height
                val endX = startX + barUnitWidth
                val endY = startY - barHeight

                drawRect(
                    color = barColor,
                    topLeft = Offset(startX, endY),
                    size = Size(barWidth, barHeight),
                    style = Stroke(2.dp.toPx())
                )
            }
        }
    }
}

@Preview
@Composable
fun SummaryPreview() {
    SummaryScreen()
}


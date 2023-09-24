package com.example.cliwatchjc.modules.tracker

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun SummaryScreen(
    weeklyData: List<Float>,
    monthlyData: List<Float>
) {
    var selectedTabIndex by remember { mutableStateOf(0) }

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
                selected = selectedTabIndex == 0,
                onClick = { selectedTabIndex = 0 }
            )
            Tab(
                text = { Text("Monthly") },
                selected = selectedTabIndex == 1,
                onClick = { selectedTabIndex = 1 }
            )
        }

        when (selectedTabIndex) {
            0 -> {
                BarChart(
                    data = weeklyData,
                    barColor = Color.Blue
                )
            }
            1 -> {
                BarChart(
                    data = monthlyData,
                    barColor = Color.Green
                )
            }
        }
    }
}


@Composable
fun BarChart(
    data: List<Float>,
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

package com.example.cliwatchjc.modules.tracker

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.input.KeyboardType
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry

@Composable
fun SummaryScreen(
    weeklyData: List<Float>,
    monthlyData: List<Float>
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        // Weekly Bar Chart
        BarChart(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            val entries = weeklyData.mapIndexed { index, value ->
                BarEntry(index.toFloat(), value)
            }
            val dataSet = BarDataSet(entries, "Weekly Data")
            data = BarData(dataSet)

            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.labelRotationAngle = 45f
            xAxis.setDrawGridLines(false)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Monthly Bar Chart
        BarChart(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            val entries = monthlyData.mapIndexed { index, value ->
                BarEntry(index.toFloat(), value)
            }
            val dataSet = BarDataSet(entries, "Monthly Data")
            data = BarData(dataSet)

            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.labelRotationAngle = 45f
            xAxis.setDrawGridLines(false)
        }
    }
}

package com.example.cliwatchjc.modules.tracker

import android.annotation.SuppressLint
import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun SummaryScreen() {
    val calculatorViewModel: CalculatorViewModel = hiltViewModel()

    // Call setSampleData to populate the sample data
    calculatorViewModel.setSampleData()

    val totalCarbonFootprint = calculatorViewModel.carbonFootprint.value
    val transportationPercentage =
        (calculatorViewModel.transportation.value / totalCarbonFootprint) * 100
    val energyPercentage = (calculatorViewModel.energyUsage.value / totalCarbonFootprint) * 100
    val foodPercentage = (calculatorViewModel.food.value / totalCarbonFootprint) * 100
    val scrollState = rememberScrollState()

    val barChartData = listOf(
        "Transportation" to transportationPercentage,
        "Energy" to energyPercentage,
        "Food" to foodPercentage
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 28.dp, end = 28.dp)
            .wrapContentSize(Alignment.Center)
            .verticalScroll(state = scrollState)
    ) {
        Spacer(modifier = Modifier.height(80.dp))
        // Title
        Text(
            text = "My Footprint",
            style = TextStyle(
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            ),

            modifier = Modifier.padding(bottom = 20.dp)
        )
        BarChart(
            data = barChartData,
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
                .height(200.dp)
                .align(Alignment.CenterHorizontally),
            axisLabelFontSize = 12.sp
        )

        Spacer(modifier = Modifier.height(40.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                // Summary Title
                Text(
                    text = "Summary",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    ),

                    modifier = Modifier.padding(bottom = 20.dp)
                )
                // Display percentage values below the bar chart
                Text(
                    text = "Transportation: ${"%.2f".format(transportationPercentage)}%",
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.Start),
                    fontSize = 16.sp
                )
                Text(
                    text = "Energy: ${"%.2f".format(energyPercentage)}%",
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.Start),
                    fontSize = 16.sp
                )
                Text(
                    text = "Food: ${"%.2f".format(foodPercentage)}%",
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.Start),
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
fun BarChart(
    data: List<Pair<String, Float>>,
    modifier: Modifier = Modifier,
    barColor: Color = Color.Blue,
    barWidth: Float = 80f,
    barSpacing: Float = 100f,
    axisLabelFontSize: TextUnit = 16.sp
) {
    val maxValue = data.maxByOrNull { it.second }?.second ?: 0f
    val maxYValue = ((maxValue / 20).toInt() + 1) * 20

    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(16.dp)
    ) {
        val availableWidth = size.width - (data.size + 1) * barSpacing
        val barUnitWidth = availableWidth / data.size

        val paint = Paint()

        // Draw y-axis labels and lines
        val yLabelSpace = 3.dp.toPx() // Adjust this value to your needs
        for (i in 0..4) {
            val labelText = (i * 20).toString()
            val textWidth = paint.measureText(labelText)
            val labelX = 0.dp.toPx()
            val labelY = size.height - (i * (size.height - yLabelSpace) / 4) - 4.dp.toPx()

            // Draw y-axis labels with adjusted font size
            paint.textSize = axisLabelFontSize.toPx()
            drawIntoCanvas { canvas ->
                canvas.nativeCanvas.drawText(labelText, labelX, labelY, paint)
            }

            // Draw y-axis lines
            val lineStartX = labelX + textWidth + 6.dp.toPx()
            val lineEndX = size.width - 6.dp.toPx()
            drawLine(
                color = Color.Gray,
                start = Offset(lineStartX, labelY),
                end = Offset(lineEndX, labelY),
                strokeWidth = 1.dp.toPx()
            )
        }

        // Draw x-axis labels and line
        data.forEachIndexed { index, (_, _) ->
            val labelText = data[index].first
            val textWidth = paint.measureText(labelText)
            val labelX =
                index * (barSpacing + barUnitWidth) + barSpacing + barWidth / 2 - textWidth / 2
            val labelY = size.height + 20.dp.toPx()

            // Draw x-axis labels with adjusted font size
            paint.textSize = axisLabelFontSize.toPx()
            drawIntoCanvas { canvas ->
                canvas.nativeCanvas.drawText(labelText, labelX, labelY, paint)
            }
        }

        data.forEachIndexed { index, (_, value) ->
            val barHeight = (value / maxYValue) * (size.height - yLabelSpace)
            val startX = index * (barSpacing + barUnitWidth) + barSpacing
            val startY = size.height - yLabelSpace
            val endX = startX + barWidth
            val endY = startY - barHeight

            // Draw the bar
            drawRect(
                color = barColor,
                topLeft = Offset(startX, endY),
                size = Size(barWidth, barHeight),
                style = Fill
            )
        }
    }
}

@Preview
@Composable
fun SummaryPreview() {
    SummaryScreen()
}


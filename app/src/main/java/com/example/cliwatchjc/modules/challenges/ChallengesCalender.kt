package com.example.cliwatchjc.modules.challenges

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Composable
fun ChallengeCalendar(
    challengeCompletionData: Map<Date, Boolean>,
    onDateClicked: (Date) -> Unit
) {
    val selectedDate = remember { mutableStateOf<Date?>(null) }

    // Get the current month and year
    val currentMonth = Calendar.getInstance().apply {
        time = selectedDate.value ?: Date()
    }.get(Calendar.MONTH)

    val currentYear = Calendar.getInstance().apply {
        time = selectedDate.value ?: Date()
    }.get(Calendar.YEAR)

    // Create a calendar instance for the current month
    val calendar = Calendar.getInstance().apply {
        set(Calendar.MONTH, currentMonth)
        set(Calendar.YEAR, currentYear)
        set(Calendar.DAY_OF_MONTH, 1)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Calendar header
        Text(
            text = SimpleDateFormat("MMMM yyyy", Locale.getDefault()).format(calendar.time),
            style = MaterialTheme.typography.displaySmall,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        // Weekday headers
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            for (dayOfWeek in Calendar.SUNDAY..Calendar.SATURDAY) {
                Text(
                    text = SimpleDateFormat("E", Locale.getDefault()).format(calendar.time),
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.weight(1f).padding(8.dp)
                )
                calendar.add(Calendar.DAY_OF_MONTH, 1)
            }
        }

        // Calendar days
        val daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        val days = (1..daysInMonth).toList()
        val gridItems = remember { mutableStateListOf<List<Int>>() }
        for (i in days.indices step 7) {
            gridItems.add(days.subList(i, minOf(i + 7, days.size)))
        }

        for (row in gridItems) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                for (day in row) {
                    val currentDate = calendar.time
                    val isCompleted = challengeCompletionData[currentDate] ?: false
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .padding(4.dp)
                            .background(if (isCompleted) Color.Green else Color.Red)
                            .clickable {
                                onDateClicked(currentDate)
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = day.toString(),
                            color = Color.White
                        )
                    }
                    calendar.add(Calendar.DAY_OF_MONTH, 1)
                }
            }
        }
    }
}

//@Preview
//@Composable
//fun ChallengeCalendarPreview() {
//    val challengeCompletionData = remember { mutableStateMapOf<Date, Boolean>() }
//    ChallengeCalendar(challengeCompletionData) { date ->
//        challengeCompletionData[date] = !challengeCompletionData[date]!!
//    }
//}

package com.example.cliwatchjc.modules.tracker

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun PersonalGoalScreen() {
    val personalGoalViewModel: PersonalGoalViewModel = hiltViewModel()
    var goalText by remember { mutableStateOf("") }
    var isDialogVisible by remember { mutableStateOf(false) }

    // Observe the goals from the ViewModel
    val goals by personalGoalViewModel.goals.collectAsState(emptyList())

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        // Title
        Text(
            text = "Personal Carbon Footprint Goals",
            style = androidx.compose.ui.text.TextStyle(
                fontSize = 24.sp, // Adjust the font size as needed
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(bottom = 16.dp)
        )
        // Display current goal (if set)
        if (goals.isNotEmpty()) {
            Text(text = "Current Goal: ${goals.first().description}")
        } else {
            Text(text = "No goals set yet.")
        }

        Button(
            onClick = { isDialogVisible = true },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Set New Goal")
        }

        if (isDialogVisible) {
            GoalDialog(
                goalText = goalText,
                onGoalSet = { newGoalText ->
                    goalText = newGoalText
                    isDialogVisible = false
                    // Save the user's goal to a database or storage
                    personalGoalViewModel.setGoal(newGoalText)
                },
                onCancel = { isDialogVisible = false }
            )
        }
    }
}

@Composable
fun GoalDialog(
    goalText: String,
    onGoalSet: (String) -> Unit,
    onCancel: () -> Unit
) {
    var goalInput by remember { mutableStateOf(goalText) }

    AlertDialog(
        onDismissRequest = { onCancel() },
        title = { Text("Set Your Carbon Reduction Goal") },
        text = {
            TextField(
                value = goalInput,
                onValueChange = { goalInput = it },
                label = { Text("Goal Description") }
            )
        },
        confirmButton = {
            Button(
                onClick = {
                    onGoalSet(goalInput)
                }
            ) {
                Text("Set Goal")
            }
        },
        dismissButton = {
            Button(
                onClick = { onCancel() }
            ) {
                Text("Cancel")
            }
        }
    )
}

@Preview
@Composable
fun PersonalGoalPreview() {
    PersonalGoalScreen()
}


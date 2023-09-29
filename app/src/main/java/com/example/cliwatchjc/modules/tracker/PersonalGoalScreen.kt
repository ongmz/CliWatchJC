package com.example.cliwatchjc.modules.tracker

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Checkbox
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cliwatchjc.data.tracker.PersonalGoal

@Composable
fun PersonalGoalScreen(personalGoalViewModel: PersonalGoalViewModel = hiltViewModel()) {
    var goalText by remember { mutableStateOf("") }
    var isDialogVisible by remember { mutableStateOf(false) }

    // Observe the goals from the ViewModel
    val goals by personalGoalViewModel.goals.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(start = 28.dp, end = 28.dp)

    ) {
        Spacer(modifier = Modifier.height(80.dp))
        // Title
        Text(
            text = "My Goals",
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Display goals
        for (goal in goals) {
            GoalItem(
                goal = goal,
                onUpdate = { updatedGoal ->
                    personalGoalViewModel.updateGoal(updatedGoal)
                },
                onDelete = { deletedGoal ->
                    personalGoalViewModel.deleteGoal(deletedGoal)
                }
            )
        }

        // Set New Goal button
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
                    personalGoalViewModel.setGoal(newGoalText, null) // Pass null as dueDate
                },
                onCancel = { isDialogVisible = false }
            )
        }
        Spacer(modifier = Modifier.height(96.dp))
    }
}

@Composable
fun GoalItem(
    goal: PersonalGoal,
    onUpdate: (PersonalGoal) -> Unit,
    onDelete: (PersonalGoal) -> Unit
) {
    var isUpdating by remember { mutableStateOf(false) }
    var updatedGoalText by remember { mutableStateOf(goal.description) }

    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically

    ) {
        Row(
            modifier = Modifier.weight(1f), // Occupy available space
            verticalAlignment = Alignment.CenterVertically // Align checkbox center vertically
        ) {
            // Checkbox
            Checkbox(
                checked = goal.completed,
                onCheckedChange = { isChecked ->
                    val updatedGoal = goal.copy(completed = isChecked)
                    onUpdate(updatedGoal)
                }
            )
            // Goal text
            Text(text = goal.description, modifier = Modifier.padding(8.dp))
        }

        Spacer(modifier = Modifier.width(8.dp))

        // Update and Delete buttons
        if (isUpdating) {
            GoalDialog(
                goalText = updatedGoalText,
                onGoalSet = { updatedText ->
                    updatedGoalText = updatedText
                    isUpdating = false
                    val updatedGoal = goal.copy(description = updatedText)
                    onUpdate(updatedGoal)
                },
                onCancel = { isUpdating = false }
            )
        } else Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp) // Add spacing between buttons
        ){
            IconButton(
                onClick = { isUpdating = true }
            ) {
                Icon(Icons.Default.Edit, contentDescription = null)
            }

            IconButton(
                onClick = { onDelete(goal) }
            ) {
                Icon(Icons.Default.Delete, contentDescription = null)
            }
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


package com.example.cliwatchjc.modules.tracker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cliwatchjc.data.tracker.PersonalGoal
import com.example.cliwatchjc.data.tracker.PersonalGoalDetails
import com.example.cliwatchjc.data.tracker.repository.PersonalGoalDetailsRepository
import com.example.cliwatchjc.data.tracker.repository.PersonalGoalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonalGoalViewModel @Inject constructor(
    private val personalGoalRepository: PersonalGoalRepository,
    private val personalGoalDetailsRepository: PersonalGoalDetailsRepository
) : ViewModel() {

    private val _goals = MutableStateFlow<List<PersonalGoal>>(emptyList())
    val goals: StateFlow<List<PersonalGoal>> = _goals.asStateFlow()

    init {
        loadGoals()
    }

    private fun loadGoals() = viewModelScope.launch(Dispatchers.IO) {
        val fetchedGoals = personalGoalRepository.getAllGoals()
        _goals.emit(fetchedGoals)
    }

    // Function to set a new goal
    fun setGoal(newGoalText: String, dueDate: Long?) {
        val newGoal = PersonalGoal(description = newGoalText, dueDate = dueDate)

        viewModelScope.launch(Dispatchers.IO) {
            personalGoalRepository.insertGoal(newGoal)
            loadGoals() // Reload the goals after adding a new one
        }
    }

    // Function to update a goal
    fun updateGoal(updatedGoal: PersonalGoal) {
        viewModelScope.launch(Dispatchers.IO) {
            personalGoalRepository.updateGoal(updatedGoal)
            loadGoals() // Reload the goals after updating
        }
    }

    // Function to delete a goal
    fun deleteGoal(goal: PersonalGoal) {
        viewModelScope.launch(Dispatchers.IO) {
            personalGoalRepository.deleteGoal(goal)
            loadGoals() // Reload the goals after deleting
        }
    }

    // Function to get personal goal details
    fun getGoalDetails(userId: Int, goalId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            val details = personalGoalDetailsRepository.getGoalDetails(userId, goalId)
            loadGoals()
        }
    }

    // Function to update personal goal details
    fun updateGoalDetails(personalGoalDetails: PersonalGoalDetails) {
        viewModelScope.launch(Dispatchers.IO) {
            personalGoalDetailsRepository.updateGoalDetails(personalGoalDetails)
            loadGoals()
        }
    }

}
package com.example.cliwatchjc.modules.tracker

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cliwatchjc.data.tracker.PersonalGoal
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
    private val personalGoalRepository: PersonalGoalRepository
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
    fun setGoal(newGoalText: String) {
        val newGoal = PersonalGoal(description = newGoalText)

        viewModelScope.launch(Dispatchers.IO) {
            personalGoalRepository.insertGoal(newGoal)
            loadGoals() // Reload the goals after adding a new one
        }
    }

}
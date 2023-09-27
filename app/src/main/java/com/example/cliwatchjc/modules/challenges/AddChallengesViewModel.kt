package com.example.cliwatchjc.modules.challenges

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cliwatchjc.data.challenges.AddChallenges
import com.example.cliwatchjc.data.challenges.repository.AddChallengesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddChallengesViewModel @Inject constructor(
private val repository: AddChallengesRepository
) : ViewModel() {

    val challenges: Flow<List<AddChallenges>> = repository.getAllChallenges()

    fun insertChallenge(challenge: AddChallenges) {
        viewModelScope.launch {
            repository.insertChallenge(challenge)
        }
    }
    fun updateChallengeStatusAndMarks(challenge: AddChallenges, newStatus: String, marks: Int) {
        viewModelScope.launch {
            // Update the challenge status and marks in the database
            repository.updateChallengeStatusAndMarks(challenge, newStatus, marks)
        }
    }
}



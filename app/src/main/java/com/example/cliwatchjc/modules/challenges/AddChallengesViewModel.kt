package com.example.cliwatchjc.modules.challenges

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cliwatchjc.data.challenges.ChallengeContentProvider
import com.example.cliwatchjc.data.challenges.Challenges
import com.example.cliwatchjc.data.challenges.repository.ChallengesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChallengesViewModel @Inject constructor(private val challengesRepository: ChallengesRepository) : ViewModel() {
    private val _challenges = MutableStateFlow<List<Challenges>>(emptyList())
    val challenges: StateFlow<List<Challenges>> = _challenges.asStateFlow()
    private val _selectedChallengeId = MutableStateFlow<Long?>(null)
    val selectedChallengeId: StateFlow<Long?> = _selectedChallengeId.asStateFlow()

    init {
        loadChallenges()
    }

    fun populateChallengeEntity(){
        viewModelScope.launch {
            challengesRepository.insertSampleChallenges()
        }
    }
    private fun loadChallenges(){
        viewModelScope.launch {
            val fetchedChallenges = challengesRepository.getAllChallenges()
            _challenges.emit(fetchedChallenges)
        }
    }

    fun getChallenge(challengesId: Long): Challenges? {
        return _challenges.value.find { it.challengesId == challengesId }
    }
    fun getChallengeContent(challengesId: Long): List<ChallengesContentComponent>? {
        return ChallengeContentProvider.getContentByChallengesId(challengesId)
    }
    fun takeChallenge(challenge: Challenges): Boolean {
        try {
            // Check if the challenge with the same ID already exists in the progress screen
            val isChallengeAlreadyExist = _challenges.value.any { it.challengesId == challenge.challengesId }

            if (isChallengeAlreadyExist) {
                // Challenge with the same ID already exists in the progress screen
                println("Challenge already exists")
                return false
            } else {
                // Challenge does not exist, add it
                val updatedChallenges = _challenges.value.toMutableList()
                updatedChallenges.add(challenge)
                _challenges.value = updatedChallenges

                // Return true to indicate success.
                return true
            }
        } catch (e: Exception) {
            return false
        }
    }
    fun setSelectedChallenge(challengesId: Long) {
        _selectedChallengeId.value = challengesId
    }

    // Add a function to update the challenge status
    // Updated function to use String for newStatus
    fun updateChallengeStatus(challenge: Challenges, newStatus: String) {
        viewModelScope.launch(Dispatchers.IO) {
            // Update the challenge status in the repository or database
            val updatedChallenge = challenge.copy(completed = newStatus)
            challengesRepository.updateChallenge(updatedChallenge)

            // Update the list of challenges with the updated challenge
            val updatedChallengesList = _challenges.value.toMutableList()
            val index = updatedChallengesList.indexOfFirst { it.challengesId == challenge.challengesId }
            if (index != -1) {
                updatedChallengesList[index] = updatedChallenge
                _challenges.value = updatedChallengesList
            }
        }
    }



}
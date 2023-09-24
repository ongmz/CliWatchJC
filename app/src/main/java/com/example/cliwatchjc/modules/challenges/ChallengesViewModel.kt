package com.example.cliwatchjc.modules.challenges

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
class ChallengesViewModel @Inject constructor(
    private val challengesRepository: ChallengesRepository
) : ViewModel() {

    private val _mutableCardData = MutableStateFlow<List<Challenges>>(emptyList())

    // Public read-only state flow exposed to the outside
    val cardData: StateFlow<List<Challenges>> = _mutableCardData.asStateFlow()

    init {
        loadChallenges()
    }

    private fun loadChallenges() = viewModelScope.launch(Dispatchers.IO) {
        val fetchedChallenges = challengesRepository.getAllChallenges()
        _mutableCardData.emit(fetchedChallenges)
    }

    fun setChallengeStatus(userId: Long, challengeId: Long, status: String) {
        viewModelScope.launch(Dispatchers.IO) {
            challengesRepository.setChallengeStatus(userId, challengeId, status)
        }
    }

    // Method to get the status of a challenge for a specific user
    suspend fun getChallengeStatus(userId: Long, challengeId: Long): String? {
        return challengesRepository.getChallengeStatus(userId, challengeId)
    }
}





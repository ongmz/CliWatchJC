package com.example.cliwatchjc.modules.challenges

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cliwatchjc.data.challenges.AddChallenges
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

    private val _mutableCardData = MutableStateFlow<List<AddChallenges>>(emptyList())

    val cardData: StateFlow<List<AddChallenges>> = _mutableCardData.asStateFlow()

    init {
        loadChallenges()
    }

    private fun loadChallenges() = viewModelScope.launch(Dispatchers.IO) {
        val fetchedChallenges = challengesRepository.getAllChallenges()
        _mutableCardData.emit(fetchedChallenges)
    }

    fun setChallengeStatus(challengeId: Long, challengeStatus: String) {
        viewModelScope.launch(Dispatchers.IO) {
            challengesRepository.setChallengeStatus(challengeId, challengeStatus)
        }
    }

    // Method to get the status of a challenge for a specific user
    fun getChallengeStatus(challengeId: Long): String? {
        return challengesRepository.getChallengeStatus(challengeId)
    }


    fun insertSampleChallenges() {
        val sampleChallenges = listOf(
            AddChallenges(
                challenges_title = "Meatless Mondays",
                challenges_desc = "Reduce your carbon footprint by cutting down on meat consumption, which has a significant environmental impact.",
                challenges_duration = "Daily"
            ),
            AddChallenges(
                challenges_title = "Zero Waste Lunches",
                challenges_desc = "Eliminate single-use plastics and opt for reusable containers and utensils to reduce waste.",
                challenges_duration = "Daily"
            ),
            AddChallenges(
                challenges_title = "Zero Waste Lunches",
                challenges_desc = "Eliminate single-use plastics and opt for reusable containers and utensils to reduce waste.",
                challenges_duration = "Daily"
            )
        )

        viewModelScope.launch(Dispatchers.IO) {
            challengesRepository.insertChallenges(sampleChallenges)
        }
    }

}





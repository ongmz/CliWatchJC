package com.example.cliwatchjc.modules.challenges

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cliwatchjc.data.challenges.ChallengeContentProvider
import com.example.cliwatchjc.data.challenges.Challenges
import com.example.cliwatchjc.data.challenges.repository.ChallengesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChallengesViewModel @Inject constructor(private val challengesRepository: ChallengesRepository) : ViewModel() {
    private val _challenges = MutableStateFlow<List<Challenges>>(emptyList())
    val challenges: StateFlow<List<Challenges>> = _challenges.asStateFlow()

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

    fun getChallenge(challengeId: Long): Challenges? {
        return _challenges.value.find { it.challengesId == challengeId }
    }
    fun getChallengeContent(challengeId: Long): List<ChallengesContentComponent>? {
        return ChallengeContentProvider.getContentByChallengesId(challengeId)
    }
}

package com.example.cliwatchjc.modules.challenges

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cliwatchjc.data.challenges.AddChallenges
import com.example.cliwatchjc.data.challenges.repository.AddChallengesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
// Shared ViewModel
class SharedChallengesViewModel @Inject constructor() : ViewModel() {
    private val _challenges = mutableStateListOf<AddChallenges>()
    val challenges: List<AddChallenges>
        get() = _challenges

    fun addChallenge(challenge: AddChallenges) {
        _challenges.add(challenge)
    }
}

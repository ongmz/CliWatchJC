package com.example.cliwatchjc.modules.challenges

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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


    init {
        // Sample data
        val challenge1 = AddChallenges(
            challengesId = 10001,
            challenges_title = "Green Commute Challenge",
            challenges_desc = "Encourage employees to use eco-friendly transportation options such as cycling, carpooling, or public transit for their daily commute. Participants will track their green commutes for a month and share their experiences on social media using a dedicated hashtag.",
            challenges_duration = "Daily",
            challenges_status = "AVAILABLE",
            marks = 100
        )

        // Insert the sample data
        insertChallenge(challenge1)
    }

    // Function to insert a challenge
    fun insertChallenge(challenge: AddChallenges) {
        viewModelScope.launch {
            repository.insertChallenge(challenge)
        }
    }

    // Function to update challenge status and marks
    fun updateChallengeStatusAndMarks(challenge: AddChallenges, newStatus: String, marks: Int) {
        viewModelScope.launch {
            repository.updateChallengeStatusAndMarks(challenge, newStatus, marks)
        }
    }
}

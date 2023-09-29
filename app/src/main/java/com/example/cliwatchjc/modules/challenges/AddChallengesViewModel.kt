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
    private val repository: AddChallengesRepository,
    private val sharedViewModel: SharedChallengesViewModel
) : ViewModel() {

    val challenges: Flow<List<AddChallenges>> = repository.getAllChallenges()


    init {
        // Sample data
        val challenge1 = AddChallenges(
            challengesId = 10001,
            challenges_title = "Green Commute Challenge",
            challenges_desc = " track their green commutes for a month and share their experiences on social media using a dedicated hashtag.track their green commutes for a month and share their experiences on social media using a dedicated hashtag.",
            challenges_duration = "Daily",
            challenges_status = "AVAILABLE",
            marks = 100
        )
        val challenge2 = AddChallenges(
            challengesId = 10002,
            challenges_title = "Challenge1",
            challenges_desc = "track their green commutes for a month and share their experiences on social media using a dedicated hashtag.",
            challenges_duration = "Daily",
            challenges_status = "AVAILABLE",
            marks = 100
        )
        val challenge3 = AddChallenges(
            challengesId = 10003,
            challenges_title = "Challenge2",
            challenges_desc = "track their green commutes for a month and share their experiences on social media using a dedicated hashtag.track their green commutes for a month and share their experiences on social media using a dedicated hashtag.track their green commutes for a month and share their experiences on social media using a dedicated hashtag.",
            challenges_duration = "Daily",
            challenges_status = "AVAILABLE",
            marks = 100
        )
        // Insert the sample data
        insertChallenge(challenge1)
        insertChallenge(challenge2)
        insertChallenge(challenge3)
    }

    // Function to insert a challenge
    fun insertChallenge(challenge: AddChallenges) {
        viewModelScope.launch {
            val isInserted = repository.insertChallenge(challenge)
            if (isInserted) {
                // Challenge was successfully added
                println("Successfully added")
                sharedViewModel.addChallenge(challenge) // Add challenge to the shared ViewModel
            } else {
                // Challenge insertion failed
                println("Fail to add")
            }
        }
    }



    fun updateChallengeStatusAndMarks(challenge: AddChallenges, newStatus: String, marks: Int) {
        viewModelScope.launch {
            repository.updateChallengeStatusAndMarks(challenge.challengesId, newStatus, marks)
        }
    }
}

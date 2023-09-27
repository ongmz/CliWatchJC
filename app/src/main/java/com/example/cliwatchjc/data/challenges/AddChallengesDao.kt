package com.example.cliwatchjc.data.challenges

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface AddChallengesDao {

    @Query("SELECT * FROM Challenges")
    fun getAllChallenges(): Flow<List<AddChallenges>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertChallenge(challenge: AddChallenges?)


}



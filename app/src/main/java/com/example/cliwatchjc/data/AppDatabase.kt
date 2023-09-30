package com.example.cliwatchjc.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cliwatchjc.data.challenges.AddChallenges
import com.example.cliwatchjc.data.challenges.AddChallengesDao
import com.example.cliwatchjc.data.challenges.Challenges
import com.example.cliwatchjc.data.challenges.ChallengesDao
import com.example.cliwatchjc.data.challenges.Leaderboard

@Database(entities = [User::class,  Challenges::class,  Leaderboard::class], version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract  fun challengesDao() : ChallengesDao

}


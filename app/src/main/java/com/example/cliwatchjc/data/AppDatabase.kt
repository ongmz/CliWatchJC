package com.example.cliwatchjc.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cliwatchjc.data.challenges.AddChallenges
import com.example.cliwatchjc.data.challenges.AddChallengesDao
import com.example.cliwatchjc.data.challenges.Leaderboard

@Database(entities = [User::class,  AddChallenges::class,  Leaderboard::class], version = 5)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract  fun challengesDao() : AddChallengesDao

}


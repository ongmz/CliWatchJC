package com.example.cliwatchjc.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cliwatchjc.data.challenges.Challenges
import com.example.cliwatchjc.data.challenges.ChallengesDao

@Database(entities = [User::class,  Challenges::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract  fun challengesDao() : ChallengesDao

}


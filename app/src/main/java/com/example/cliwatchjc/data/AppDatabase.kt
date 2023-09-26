package com.example.cliwatchjc.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cliwatchjc.data.challenges.AddChallenges
import com.example.cliwatchjc.data.challenges.ChallengeStatusEntity
import com.example.cliwatchjc.data.challenges.ChallengesDao
import com.example.cliwatchjc.data.challenges.Leaderboard
import com.example.cliwatchjc.data.education.Article
import com.example.cliwatchjc.data.education.EducationDao
import com.example.cliwatchjc.data.education.UserQuizScore

@Database(entities = [User::class, Article::class, UserQuizScore::class, AddChallenges::class, ChallengeStatusEntity::class, Leaderboard::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun educationDao(): EducationDao
    abstract  fun challengesDao() : ChallengesDao

}


package com.example.cliwatchjc.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cliwatchjc.data.education.Article
import com.example.cliwatchjc.data.education.ClimateNews
import com.example.cliwatchjc.data.education.EducationDao
import com.example.cliwatchjc.data.education.Option
import com.example.cliwatchjc.data.education.Question
import com.example.cliwatchjc.data.education.UserQuestionAttempt
import com.example.cliwatchjc.data.education.UserQuizScore

@Database(entities = [User::class, Article::class, UserQuizScore::class, UserQuestionAttempt::class, Question::class, Option::class,
                      ClimateNews::class], version = 6)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun educationDao(): EducationDao
}


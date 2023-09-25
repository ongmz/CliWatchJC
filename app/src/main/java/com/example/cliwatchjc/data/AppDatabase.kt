package com.example.cliwatchjc.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.cliwatchjc.data.education.Article
import com.example.cliwatchjc.data.education.EducationDao
import com.example.cliwatchjc.data.education.UserQuizScore
import com.example.cliwatchjc.data.tracker.personalGoal.PersonalGoal
import com.example.cliwatchjc.data.tracker.personalGoal.PersonalGoalDetails
import com.example.cliwatchjc.data.tracker.personalGoal.PersonalGoalDao
import com.example.cliwatchjc.data.tracker.personalGoal.PersonalGoalDetailsDao
import com.example.cliwatchjc.modules.tracker.Converters

@Database(entities = [User::class, Article::class, UserQuizScore::class,
    PersonalGoal::class, PersonalGoalDetails::class], version = 3)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun educationDao(): EducationDao
    abstract fun personalGoalDao(): PersonalGoalDao
    abstract fun personalGoalDetailsDao(): PersonalGoalDetailsDao

}


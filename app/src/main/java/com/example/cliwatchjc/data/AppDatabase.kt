package com.example.cliwatchjc.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.cliwatchjc.data.education.Article
import com.example.cliwatchjc.data.education.ClimateNews
import com.example.cliwatchjc.data.education.EducationDao
import com.example.cliwatchjc.data.education.Option
import com.example.cliwatchjc.data.education.Question
import com.example.cliwatchjc.data.education.UserQuestionAttempt
import com.example.cliwatchjc.data.education.UserQuizScore
import com.example.cliwatchjc.data.tracker.PersonalGoal
import com.example.cliwatchjc.data.tracker.PersonalGoalDetails
import com.example.cliwatchjc.data.tracker.PersonalGoalDao
import com.example.cliwatchjc.data.tracker.PersonalGoalDetailsDao
import com.example.cliwatchjc.modules.tracker.Converters
import com.example.cliwatchjc.data.challenges.Challenges
import com.example.cliwatchjc.data.challenges.ChallengesDao


@Database(entities = [User::class, Article::class, UserQuizScore::class, UserQuestionAttempt::class, Question::class, Option::class,
                      ClimateNews::class, PersonalGoal::class, PersonalGoalDetails::class, Challenges::class], version = 11)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun educationDao(): EducationDao
    abstract fun personalGoalDao(): PersonalGoalDao
    abstract fun personalGoalDetailsDao(): PersonalGoalDetailsDao
    abstract fun challengesDao(): ChallengesDao

}


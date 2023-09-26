package com.example.cliwatchjc.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.cliwatchjc.data.tracker.PersonalGoal
import com.example.cliwatchjc.data.tracker.PersonalGoalDetails
import com.example.cliwatchjc.data.tracker.PersonalGoalDao
import com.example.cliwatchjc.data.tracker.PersonalGoalDetailsDao
import com.example.cliwatchjc.modules.tracker.Converters

@Database(entities = [User::class,
    PersonalGoal::class, PersonalGoalDetails::class], version = 7)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun personalGoalDao(): PersonalGoalDao
    abstract fun personalGoalDetailsDao(): PersonalGoalDetailsDao

}


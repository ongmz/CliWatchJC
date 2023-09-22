package com.example.cliwatchjc.data.trackerData.personalGoal

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.cliwatchjc.modules.tracker.Converters

@Database(entities = [PersonalGoal::class, PersonalGoalDetails::class], version = 1)
@TypeConverters(Converters::class)
abstract class PersonalGoalDatabase : RoomDatabase() {

    abstract fun personalGoalDAO(): PersonalGoalDAO

    abstract fun personalGoalDetailsDAO(): PersonalGoalDetailsDAO

    companion object {
        private var instance: PersonalGoalDatabase? = null

        fun getInstance(context: Context): PersonalGoalDatabase {
            if (instance == null) {
                synchronized(PersonalGoalDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PersonalGoalDatabase::class.java, "goals_database"
                    ).fallbackToDestructiveMigration()
                        .build()
                }
            }
            return instance!!
        }

        fun destroyInstance() {
            instance = null
        }
    }
}

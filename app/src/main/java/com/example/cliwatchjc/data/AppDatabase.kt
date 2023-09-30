package com.example.cliwatchjc.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.cliwatchjc.data.challenges.Challenges
import com.example.cliwatchjc.data.challenges.ChallengesDao


@Database(entities = [User::class,  Challenges::class], version = 4, exportSchema = false)


abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun challengesDao(): ChallengesDao

}

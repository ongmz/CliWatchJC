package com.example.cliwatchjc.data.trackerData.calculator

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cliwatchjc.data.User
import com.example.cliwatchjc.data.UserDao

@Database(entities = [User :: class], version = 1)
abstract class CalculatorDatabase : RoomDatabase() {

    abstract fun userDao() : UserDao

    companion object{

        @Volatile
        private var INSTANCE : CalculatorDatabase? = null

        fun getDatabase(context: Context): CalculatorDatabase {

            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CalculatorDatabase::class.java,
                    "user_database"
                ).build()
                INSTANCE = instance
                return instance
            }

        }

    }

}
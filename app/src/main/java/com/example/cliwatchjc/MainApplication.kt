package com.example.cliwatchjc

import android.app.Application
import com.example.cliwatchjc.data.AppDatabase
import com.example.cliwatchjc.data.tracker.PersonalGoal
import com.example.cliwatchjc.data.tracker.PersonalGoalDetails
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltAndroidApp
class MainApplication : Application() {

    @Inject
    lateinit var database: AppDatabase

    override fun onCreate() {
        super.onCreate()

        // Insert sample data in a coroutine (with checks if needed)
        runBlocking {
            launch(Dispatchers.IO) {
                val examplePersonalGoal = PersonalGoal(
                    title = "Reduce using car to go to work",
                    description = "Walk for this entire week"
                )
                database.personalGoalDao().insertGoal(examplePersonalGoal)
            }
        }
    }
}

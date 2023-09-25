package com.example.cliwatchjc

import android.app.Application
import com.example.cliwatchjc.data.AppDatabase
import com.example.cliwatchjc.data.education.Article
import com.example.cliwatchjc.data.tracker.personalGoal.PersonalGoal
import com.example.cliwatchjc.data.tracker.personalGoal.PersonalGoalDetails
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
                val exampleArticle = Article(
                    title = "Example Article Title",
                    content = "Lorem ipsum dolor sit amet...",
                    maxScore = 5
                )
                database.educationDao().insertArticle(exampleArticle)

                val examplePersonalGoal = PersonalGoal(
                    title = "Example Goal Title",
                    description = "Example Goal Description"
                )
                database.personalGoalDao().insertGoal(examplePersonalGoal)

                val examplePersonalGoalDetails = PersonalGoalDetails(
                    goalId = examplePersonalGoal.id,
                    notes = "Example Goal Details"
                )
                database.personalGoalDetailsDao().insertGoalDetails(examplePersonalGoalDetails)
            }
        }
    }
}

package com.example.cliwatchjc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.room.Room
import com.example.cliwatchjc.data.AppDatabase
import com.example.cliwatchjc.data.education.Article
import com.example.cliwatchjc.data.education.repository.ArticleRepository
import com.example.compose.AppTheme
import com.example.cliwatchjc.data.tracker.repository.PersonalGoalDetailsRepository
import com.example.cliwatchjc.data.tracker.repository.PersonalGoalRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var educationRepository: ArticleRepository
    lateinit var personalGoalRepository: PersonalGoalRepository
    lateinit var personalGoalDetailsRepository: PersonalGoalDetailsRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme{
                MyApp()
            }
        }
    }
}


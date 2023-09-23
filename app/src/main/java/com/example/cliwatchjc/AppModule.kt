package com.example.cliwatchjc

import android.app.Application
import androidx.room.Room
import com.example.cliwatchjc.data.AppDatabase
import com.example.cliwatchjc.data.education.repository.ArticleRepository
import com.example.cliwatchjc.data.tracker.personalGoal.PersonalGoalRepository
import com.example.cliwatchjc.data.tracker.personalGoal.PersonalGoalDetailsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application,
            AppDatabase::class.java, "app-database"
        )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }


    @Provides
    @Singleton
    fun provideArticleRepository(database: AppDatabase): ArticleRepository {
        return ArticleRepository(database.educationDao())
    }

    @Provides
    @Singleton
    fun providePersonalGoalRepository(database: AppDatabase): PersonalGoalRepository {
        return PersonalGoalRepository(database.personalGoalDao())
    }

    @Provides
    @Singleton
    fun providePersonalGoalDetailsRepository(database: AppDatabase): PersonalGoalDetailsRepository {
        return PersonalGoalDetailsRepository(database.personalGoalDetailsDao())
    }
}
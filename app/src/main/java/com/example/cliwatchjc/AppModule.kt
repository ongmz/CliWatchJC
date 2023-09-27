package com.example.cliwatchjc

import android.app.Application
import androidx.room.Room
import com.example.cliwatchjc.data.AppDatabase
import com.example.cliwatchjc.data.challenges.repository.AddChallengesRepository
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
    fun provideChallengesRepository(database: AppDatabase): AddChallengesRepository {
        return AddChallengesRepository(database.challengesDao())
    }
}
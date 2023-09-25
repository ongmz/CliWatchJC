package com.example.cliwatchjc

import android.app.Application
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.cliwatchjc.data.AppDatabase
import com.example.cliwatchjc.data.education.repository.ArticleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideUserManager(): UserManager {
        return UserManager()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application,
            AppDatabase::class.java, "app-database"
        )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideArticleRepository(database: AppDatabase): ArticleRepository {
        return ArticleRepository(database.educationDao())
    }
}


package com.example.cliwatchjc

import android.app.Application
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.cliwatchjc.data.AppDatabase
import com.example.cliwatchjc.data.education.EducationDao
import com.example.cliwatchjc.data.education.NewsApi
import com.example.cliwatchjc.data.education.repository.ArticleRepository
import com.example.cliwatchjc.data.education.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    const val BASE_URL = "https://newsapi.org/v2/"
    const val API_KEY = "YOUR_API_KEY"

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
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideEducationDao(database: AppDatabase): EducationDao {
        return database.educationDao()
    }

    @Provides
    @Singleton
    fun provideNewsApi(retrofit: Retrofit): NewsApi {
        return retrofit.create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideArticleRepository(database: AppDatabase): ArticleRepository {
        return ArticleRepository(database.educationDao())
    }

    @Provides
    @Singleton
    fun provideNewsRepository(newsApi: NewsApi, educationDao: EducationDao): NewsRepository {
        return NewsRepository(newsApi, educationDao)
    }
}


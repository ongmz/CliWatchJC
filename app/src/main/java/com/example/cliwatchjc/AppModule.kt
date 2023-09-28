package com.example.cliwatchjc

import android.app.Application
import androidx.room.Room
import com.example.cliwatchjc.data.AppDatabase
import com.example.cliwatchjc.data.UserDao
import com.example.cliwatchjc.data.education.EducationDao
import com.example.cliwatchjc.data.education.NewsApi
import com.example.cliwatchjc.data.education.repository.ArticleRepository
import com.example.cliwatchjc.data.education.repository.NewsRepository
import com.example.cliwatchjc.data.tracker.PersonalGoalDao
import com.example.cliwatchjc.data.tracker.PersonalGoalDetailsDao
import com.example.cliwatchjc.data.tracker.repository.PersonalGoalDetailsRepository
import com.example.cliwatchjc.data.tracker.repository.PersonalGoalRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    const val BASE_URL = "https://newsapi.org/v2/"
    const val API_KEY = "a9736382f93a4ca9814b0940d1c531d1"

    @Provides
    @Singleton
    fun provideUserDao(database: AppDatabase): UserDao {
        return database.userDao()
    }

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
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor { chain ->
                val original = chain.request()
                val originalHttpUrl = original.url

                val url = originalHttpUrl.newBuilder()
                    .addQueryParameter("apiKey", API_KEY)
                    .build()

                val request = original.newBuilder()
                    .url(url)
                    .build()
                chain.proceed(request)
            }
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    //Ming Zheng
    @Provides
    @Singleton
    fun provideArticleRepository(educationDao: EducationDao): ArticleRepository {
        return ArticleRepository(educationDao)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(newsApi: NewsApi, educationDao: EducationDao): NewsRepository {
        return NewsRepository(newsApi, educationDao)
    }

    @Provides
    @Singleton
    fun provideEducationDao(database: AppDatabase): EducationDao {
        return database.educationDao()
    }

    @Provides
    @Singleton
    fun provideNewsApi(retrofit: Retrofit): NewsApi {
        return retrofit.create(NewsApi::class.java)
    }

    //Natalie
    @Provides
    @Singleton
    fun providePersonalGoalRepository( personalGoalDao: PersonalGoalDao ): PersonalGoalRepository {
        return PersonalGoalRepository(personalGoalDao)
    }

    @Provides
    @Singleton
    fun providePersonalGoalDao(database: AppDatabase): PersonalGoalDao {
        return database.personalGoalDao()
    }

    @Provides
    @Singleton
    fun providePersonalGoalDetailsRepository(database: AppDatabase): PersonalGoalDetailsRepository {
        return PersonalGoalDetailsRepository(database.personalGoalDetailsDao())
    }

    @Provides
    @Singleton
    fun providePersonalGoalDetailsDao(database: AppDatabase): PersonalGoalDetailsDao {
        return database.personalGoalDetailsDao()
    }
}
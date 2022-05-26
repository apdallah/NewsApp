package com.apdallahy.newsapp.di

import com.apdallahy.newsapp.data.remote.NewsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideCoroutineScope():CoroutineScope{
        return CoroutineScope(Dispatchers.IO + Job())
    }
    @Provides
    @Singleton
    fun provideRetrofit(): NewsService {
        val API_BASE_URL = "   https://saurav.tech/NewsAPI/"

        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient
            .Builder().addInterceptor(logger)
        val builder = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create()
            )


        val retrofit = builder
            .client(
                httpClient.build()
            )
            .build()

        return retrofit.create(NewsService::class.java)
    }

}
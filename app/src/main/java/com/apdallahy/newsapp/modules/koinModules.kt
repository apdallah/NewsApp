package com.apdallahy.newsapp.modules

import com.apdallahy.newsapp.UI.NewsViewModel
import com.apdallahy.newsapp.data.remote.NewsService
import com.apdallahy.newsapp.data.repos.NewsRepositoryImpl
import com.apdallahy.newsapp.data.repos.StocksRepositoryImpl
import com.apdallahy.newsapp.data.usecases.GetAllNewsUseCase
import com.apdallahy.newsapp.data.usecases.GetStocksFromAsset
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import org.koin.dsl.module
import retrofit2.Retrofit

import retrofit2.converter.gson.GsonConverterFactory

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel


val koinModules =
    module {
        single { NewsRepositoryImpl(get()) }
        single { StocksRepositoryImpl(get()) }
        single { CoroutineScope(Dispatchers.IO + Job()) }
        single { GetAllNewsUseCase(get(), get()) }
        single { GetStocksFromAsset(get(), get()) }
        single { provideRetrofit() }
        viewModel { NewsViewModel(get(), get(),get()) }
    }

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
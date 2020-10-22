package com.example.retrofitdemo.di

import com.example.retrofitdemo.ui.QuestionViewModel
import com.example.retrofitdemo.repository.QuestionRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.retrofitdemo.remote.APIService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

const val BASE_URL = "https://api.stackexchange.com"

val appModule = module {
    single { providerGson() }
    single { providerRetrofit(get()) }
    single { providerApiService(get()) }
    single { QuestionRepository(get()) }
    single { providerHttp() }
    viewModel { QuestionViewModel(get()) }
}

fun providerApiService(retrofit: Retrofit): APIService =
    retrofit.create(APIService::class.java)

fun providerRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create(gson))
    .build()

fun providerGson(): Gson = GsonBuilder().create()

fun providerHttp(): OkHttpClient {
    val okHttpBuilder = OkHttpClient.Builder()
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY
    okHttpBuilder.addInterceptor(logging)
    return okHttpBuilder.build()
}



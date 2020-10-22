package com.example.retrofitdemo.di

import com.example.retrofitdemo.ui.QuestionViewModel
import com.example.retrofitdemo.repository.QuestionRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.retrofitdemo.remote.APIService

private const val BASE_URL = "https://api.stackexchange.com"

val appModule = module {
    single { providerRetrofit() }
    //single { providerGson() }
    single { providerApiService(get()) }
    single { QuestionRepository(get()) }
    viewModel { QuestionViewModel(get()) }
}

fun providerApiService(retrofit: Retrofit): APIService =
    retrofit.create(APIService::class.java)

fun providerRetrofit(): Retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

//fun providerGson(): Gson = GsonBuilder().create()



package com.jamsmendez.starwars.di

import com.jamsmendez.starwars.data.remote.StarWarsApi
import com.jamsmendez.starwars.util.Constants.URL_API_STAR_WARS
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
object NetworkModule {

  @Singleton
  @Provides
  fun provideRetrofit(): Retrofit {
    val okHttpClient: OkHttpClient.Builder = OkHttpClient.Builder()
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY
    okHttpClient.addInterceptor(logging)

    return Retrofit
      .Builder()
      .baseUrl(URL_API_STAR_WARS)
      .addConverterFactory(GsonConverterFactory.create())
      .client(okHttpClient.build())
      .build()
  }

  @Singleton
  @Provides
  fun provideStarWarsApiClient(retrofit: Retrofit): StarWarsApi {
    return  retrofit.create(StarWarsApi::class.java)
  }

}
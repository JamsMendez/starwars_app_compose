package com.jamsmendez.starwars.di

import android.content.Context
import androidx.room.Room
import com.jamsmendez.starwars.data.local.StarWarsDatabase
import com.jamsmendez.starwars.util.Constants.STAR_WARS_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

  @Provides
  @Singleton
  fun provideDatabase(
    @ApplicationContext context: Context
  ): StarWarsDatabase {
    return Room.databaseBuilder(
      context,
      StarWarsDatabase::class.java,
      STAR_WARS_DATABASE,
    ).build()
  }

}
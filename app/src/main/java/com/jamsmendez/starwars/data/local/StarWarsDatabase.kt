package com.jamsmendez.starwars.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jamsmendez.starwars.data.model.CharacterModel
import com.jamsmendez.starwars.data.model.CharacterRemoteKeys

@Database(entities = [CharacterModel::class, CharacterRemoteKeys::class], version = 1)
abstract class StarWarsDatabase: RoomDatabase() {

  abstract fun characterDao(): CharacterDao
  abstract fun characterRemoteKeysDao(): CharacterRemoteKeysDao
}
package com.jamsmendez.starwars.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jamsmendez.starwars.data.model.CharacterModel

@Dao
interface CharacterDao {
  @Query("SELECT * FROM characters_table")
  fun getAllCharacters(): PagingSource<Int, CharacterModel>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun addCharacters(characters: List<CharacterModel>)

  @Query("DELETE FROM characters_table")
  suspend fun deleteAllCharacters()
}
package com.jamsmendez.starwars.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jamsmendez.starwars.data.model.CharacterRemoteKeys

@Dao
interface CharacterRemoteKeysDao {

  @Query("SELECT * FROM characters_remote_keys_table WHERE id =:id")
  suspend fun getRemoteKeys(id: String): CharacterRemoteKeys

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun addAllRemoteKeys(remoteKeys: List<CharacterRemoteKeys>)

  @Query("DELETE FROM characters_remote_keys_table")
  suspend fun deleteAllRemoteKeys()

}
package com.jamsmendez.starwars.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jamsmendez.starwars.util.Constants.CHARACTERS_REMOTE_KEYS_TABLE

@Entity(tableName = CHARACTERS_REMOTE_KEYS_TABLE)
data class CharacterRemoteKeys(
  @PrimaryKey(autoGenerate = false)
  val id: String,
  val prevPage: Int?,
  val nextPage: Int?
)
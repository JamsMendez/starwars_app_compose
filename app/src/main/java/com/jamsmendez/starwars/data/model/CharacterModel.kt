package com.jamsmendez.starwars.data.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.jamsmendez.starwars.util.Constants.CHARACTERS_TABLE

@Entity(tableName = CHARACTERS_TABLE)
data class CharacterModel(
  @PrimaryKey(autoGenerate = false)
  @SerializedName("id") var id: String,
  @SerializedName("url") var url: String,
  @SerializedName("name") var name: String,
  @SerializedName("image") var image: String,
  @SerializedName("specie")
  @Embedded
  var specie: SpecieOneModel,
)

data class SpecieOneModel(
  @SerializedName("name")
  @ColumnInfo(name = "specie_name")
  var name: String,
)

data class PeoplesModel(
  @SerializedName("peoples") var peoples: CharactersModel = CharactersModel(
    listOf(), null, null
  ),
)

data class CharacterFullModel(
  @SerializedName("id") var id: Int = 0,
  @SerializedName("url") var url: String = "",
  @SerializedName("name") var name: String = "",
  @SerializedName("height") var height: String = "",
  @SerializedName("mass") var mass: String = "",
  @SerializedName("hairColor") var hairColor: String = "",
  @SerializedName("skinColor") var skinColor: String = "",
  @SerializedName("eyeColor") var eyeColor: String = "",
  @SerializedName("birthYear") var birthYear: String = "",
  @SerializedName("gender") var gender: String = "",
  @SerializedName("image") var image: String = "",

  @SerializedName("homeworld") var homeworld: HomeworldModel? = null,
  @SerializedName("specie") var specie: SpecieModel? = null,
  @SerializedName("films") var films: List<FilmModel> = listOf(),
  @SerializedName("vehicles") var vehicles: List<VehicleModel> = listOf(),
  @SerializedName("starships") var starships: List<StarshipModel> = listOf(),
)

data class PeopleModel(
  @SerializedName("people") var people: CharacterFullModel = CharacterFullModel(),
)

data class CharactersModel(
  @SerializedName("results") var characters: List<CharacterModel> = emptyList(),
  @SerializedName("next") var next: Int?,
  @SerializedName("previous") var previous: Int?,
)
package com.jamsmendez.starwars.data.model

import com.google.gson.annotations.SerializedName

data class SpecieModel(
  @SerializedName("id") var id: Int,
  @SerializedName("url") var url: Int,
  @SerializedName("name") var name: String,
)
package com.jamsmendez.starwars.data.model

import com.google.gson.annotations.SerializedName

data class HomeworldModel(
  @SerializedName("id") var id: Int,
  @SerializedName("url") var url: Int,
  @SerializedName("name") var name: String,
  @SerializedName("climate") var climate: String,
  @SerializedName("diameter") var diameter: String,
  @SerializedName("gravity") var gravity: String,
  @SerializedName("orbitalPeriod") var orbitalPeriod: String,
  @SerializedName("population") var population: String,
  @SerializedName("rotationPeriod") var rotationPeriod: String,
  @SerializedName("surfaceWater") var surfaceWater: String,
  @SerializedName("terrain") var terrain: String,
  @SerializedName("image") var image: String,
)

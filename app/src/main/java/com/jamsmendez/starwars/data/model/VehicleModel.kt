package com.jamsmendez.starwars.data.model

import com.google.gson.annotations.SerializedName

data class VehicleModel(
  @SerializedName("id") var id: Int,
  @SerializedName("url") var url: Int,
  @SerializedName("name") var name: String,
  @SerializedName("model") var model: String,
  @SerializedName("cargoCapacity") var cargoCapacity: String,
  @SerializedName("consumables") var consumables: String,
  @SerializedName("costInCredit") var costInCredit: String,
  @SerializedName("crew") var crew: String,
  @SerializedName("length") var length: String,
  @SerializedName("manufacture") var manufacture: String,
  @SerializedName("maxAtmospheringSpeed") var maxAtmospheringSpeed: String,
  @SerializedName("passenger") var passenger: String,
  @SerializedName("vehicleClass") var vehicleClass: String,
  @SerializedName("image") var image: String,
)
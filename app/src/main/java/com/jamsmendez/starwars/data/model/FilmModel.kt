package com.jamsmendez.starwars.data.model

import com.google.gson.annotations.SerializedName

data class FilmModel(
  @SerializedName("id") var id: Int,
  @SerializedName("url") var url: String,
  @SerializedName("title") var title: String,
  @SerializedName("episodeId") var episodeId: String,
  @SerializedName("director") var director: String,
  @SerializedName("producer") var producer: String,
  @SerializedName("releaseDate") var releaseDate: String,
  @SerializedName("openCrawl") var openCrawl: String,
  @SerializedName("image") var image: String,
)

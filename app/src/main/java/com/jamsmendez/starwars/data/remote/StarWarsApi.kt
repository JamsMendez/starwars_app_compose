package com.jamsmendez.starwars.data.remote

import com.jamsmendez.starwars.data.model.PeopleModel
import com.jamsmendez.starwars.data.model.PeoplesModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface StarWarsApi {
  @GET("/api/graphql")
  suspend fun getAllCharactersPage(
    @Query("query") query: String,
  ): Response<PeoplesModel>

  @GET("/api/graphql")
  suspend fun getSearchCharactersPage(
    @Query("query") query: String,
  ): Response<PeoplesModel>

  @GET("/api/graphql")
  suspend fun getCharacter(
    @Query("query") query: String,
  ): Response<PeopleModel>
}
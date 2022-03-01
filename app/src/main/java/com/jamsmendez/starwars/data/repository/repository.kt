package com.jamsmendez.starwars.data.repository

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.jamsmendez.starwars.data.local.StarWarsDatabase
import com.jamsmendez.starwars.data.model.CharacterFullModel
import com.jamsmendez.starwars.data.model.CharacterModel
import com.jamsmendez.starwars.data.model.PeopleModel
import com.jamsmendez.starwars.data.paging.SearchPagingSource
import com.jamsmendez.starwars.data.paging.StarWarsRemoteMediator
import com.jamsmendez.starwars.data.remote.StarWarsApi
import com.jamsmendez.starwars.util.Constants.ITEMS_PER_PAGE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ExperimentalPagingApi
class Repository @Inject constructor(
  private val starWarsApi: StarWarsApi,
  private val starWarsDatabase: StarWarsDatabase
) {

  fun getAllCharacters(): Flow<PagingData<CharacterModel>> {
    val pagingSourceFactory = { starWarsDatabase.characterDao().getAllCharacters() }
    val config = PagingConfig(
      pageSize = ITEMS_PER_PAGE,
      prefetchDistance = ITEMS_PER_PAGE + 1,
      initialLoadSize = ITEMS_PER_PAGE - 1
    )

    return Pager(
      config = config,
      remoteMediator = StarWarsRemoteMediator(
        starWarsApi= starWarsApi,
        starWarsDatabase = starWarsDatabase
      ),
      pagingSourceFactory = pagingSourceFactory
    ).flow
  }

  fun searchCharacters(search: String): Flow<PagingData<CharacterModel>> {
    val query = """
     query {
       peoples(search: "$search") {
         next
         previous
         results {
          id
          url
          name
          image
          specie {
            name
          }
         }
       }
     }"""

    val config = PagingConfig(
      pageSize = ITEMS_PER_PAGE,
      prefetchDistance = ITEMS_PER_PAGE + 1,
      initialLoadSize = ITEMS_PER_PAGE - 1
    )

    return Pager(
      config = config,
      pagingSourceFactory = {
        SearchPagingSource(starWarsApi = starWarsApi, query = query)
      }
    ).flow
  }

  suspend fun getCharacter(id: Int): CharacterFullModel {
    return withContext(Dispatchers.IO) {
      val query = """
       query {
         people(id: $id) {
           id
           url
           name
           height
           mass
           skinColor
           hairColor
           eyeColor
           birthYear
           gender
           image
           films {
             id
             title
             image
             episodeId
             releaseDate
           }
           vehicles {
             id
             name
             image
             model 
             vehicleClass
           }
           starships {
             id
             name 
             image
             model 
             starshipClass
           }
         }
       }"""

      val response = starWarsApi.getCharacter(query = query)
      val peopleModel = response.body() ?: PeopleModel()
      peopleModel.people
    }
  }
}
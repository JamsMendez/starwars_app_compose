package com.jamsmendez.starwars.data.paging

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.jamsmendez.starwars.data.local.StarWarsDatabase
import com.jamsmendez.starwars.data.remote.StarWarsApi
import com.jamsmendez.starwars.data.model.CharacterModel
import com.jamsmendez.starwars.data.model.CharacterRemoteKeys
import com.jamsmendez.starwars.data.model.PeoplesModel

@ExperimentalPagingApi
class StarWarsRemoteMediator(
  private val starWarsApi: StarWarsApi,
  private val starWarsDatabase: StarWarsDatabase
) : RemoteMediator<Int, CharacterModel>() {

  private val characterDao = starWarsDatabase.characterDao()
  private val characterRemoteKeysDao = starWarsDatabase.characterRemoteKeysDao()

  override suspend fun load(
    loadType: LoadType,
    state: PagingState<Int, CharacterModel>
  ): MediatorResult {
    return try {
      val currentPage = when (loadType) {
        LoadType.REFRESH -> {
          val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)

          remoteKeys?.nextPage?.minus(1) ?: 1
        }
        LoadType.PREPEND -> {
          val remoteKeys = getRemoteKeyForFirstItem(state)

          val prevPage = remoteKeys?.prevPage
            ?: return MediatorResult.Success(
              endOfPaginationReached = remoteKeys != null
            )
          prevPage
        }
        LoadType.APPEND -> {
          val remoteKeys = getRemoteKeyForLastItem(state)

          val nextPage = remoteKeys?.nextPage
            ?: return MediatorResult.Success(
              endOfPaginationReached = remoteKeys != null
            )
          nextPage
        }
      }

      val query = """
       query {
         peoples(page: $currentPage) {
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

      val response = starWarsApi.getAllCharactersPage(query = query)
      val peopleModel = response.body() ?: PeoplesModel()
      val result = peopleModel.peoples
      val endOfPaginationReached = result.characters.isEmpty() || result.next == null

      val prevPage = if (currentPage == 1) null else currentPage - 1
      val nextPage = if (endOfPaginationReached) null else currentPage + 1

      starWarsDatabase.withTransaction {
        if (loadType == LoadType.REFRESH) {
          characterDao.deleteAllCharacters()
          characterRemoteKeysDao.deleteAllRemoteKeys()
        }
        val keys = result.characters.map { characterModel ->
          CharacterRemoteKeys(
            id = characterModel.id,
            prevPage = prevPage,
            nextPage = nextPage
          )
        }
        characterRemoteKeysDao.addAllRemoteKeys(remoteKeys = keys)
        characterDao.addCharacters(characters = result.characters)
      }

      MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
    } catch (e: Exception) {
      Log.e("JamsMendez", e.toString())
      return MediatorResult.Error(e)
    }
  }

  private suspend fun getRemoteKeyClosestToCurrentPosition(
    state: PagingState<Int, CharacterModel>
  ): CharacterRemoteKeys? {
    return state.anchorPosition?.let { position ->
      state.closestItemToPosition(position)?.id?.let { id ->
        characterRemoteKeysDao.getRemoteKeys(id = id)
      }
    }
  }

  private suspend fun getRemoteKeyForFirstItem(
    state: PagingState<Int, CharacterModel>
  ): CharacterRemoteKeys? {
    return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
      ?.let { characterModel ->
        characterRemoteKeysDao.getRemoteKeys(id = characterModel.id)
      }
  }

  private suspend fun getRemoteKeyForLastItem(
    state: PagingState<Int, CharacterModel>
  ): CharacterRemoteKeys? {
    return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
      ?.let { characterModel ->
        characterRemoteKeysDao.getRemoteKeys(id = characterModel.id)
      }
  }

}
package com.jamsmendez.starwars.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jamsmendez.starwars.data.model.CharacterModel
import com.jamsmendez.starwars.data.model.PeoplesModel
import com.jamsmendez.starwars.data.remote.StarWarsApi

class SearchPagingSource(
  private val starWarsApi: StarWarsApi,
  private val query: String
) : PagingSource<Int, CharacterModel>() {

  override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterModel> {
    val currentPage = params.key ?: 1
    return try {
      val response = starWarsApi.getSearchCharactersPage(query = query)
      val peopleModel = response.body() ?: PeoplesModel()
      val result = peopleModel.peoples
      val endOfPaginationReached = result.characters.isEmpty() || (result.next == null)

      if (result.characters.isNotEmpty()) {
        LoadResult.Page(
          data = result.characters,
          prevKey = if (currentPage == 1) null else currentPage - 1,
          nextKey = if (endOfPaginationReached) null else currentPage + 1
        )
      } else {
        LoadResult.Page(
          data = emptyList(),
          prevKey = null,
          nextKey = null
        )
      }
    } catch (e: Exception) {
      Log.e("JamsMendez", e.toString())

      LoadResult.Error(e)
    }
  }

  override fun getRefreshKey(state: PagingState<Int, CharacterModel>): Int? {
    return state.anchorPosition
  }
}
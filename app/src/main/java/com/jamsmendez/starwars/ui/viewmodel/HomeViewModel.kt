package com.jamsmendez.starwars.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.jamsmendez.starwars.data.model.CharacterModel
import com.jamsmendez.starwars.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
/*
@HiltViewModel
class CharacterViewModel @Inject constructor(
  private val getCharacterUseCase: GetCharacterUseCase
): ViewModel() {

  val listCharacterModel = MutableLiveData<List<CharacterModel>>(listOf())

  fun onCreate() {
    viewModelScope.launch {
      val result: CharactersModel = getCharacterUseCase()

      if (result.characters.isNullOrEmpty()) {
        listCharacterModel.postValue(listOf())
      } else {
        listCharacterModel.postValue(result.characters)
      }
    }
  }
}
 */

@ExperimentalPagingApi
@HiltViewModel
class HomeViewModel @Inject constructor(
  private val repository: Repository
): ViewModel() {
  val getAllCharacters = repository.getAllCharacters()

  private val _searchQuery = mutableStateOf("")
  private val _hasSearch = mutableStateOf(false)

  val searchQuery = _searchQuery
  val hasSearch = _hasSearch

  private val _searchedCharacters = MutableStateFlow<PagingData<CharacterModel>>(PagingData.empty())
  val searchedCharacters = _searchedCharacters

  fun updateSearchQuery(query: String) {
    _searchQuery.value = query
  }

  fun setSearch(value: Boolean) {
    _hasSearch.value = value
  }

  fun searchCharacters(search: String) {
    _hasSearch.value = true

    viewModelScope.launch {
      repository.searchCharacters(search = search).cachedIn(viewModelScope).collect {
        _searchedCharacters.value = it
      }
    }
  }
}
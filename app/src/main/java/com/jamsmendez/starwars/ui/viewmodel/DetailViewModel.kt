package com.jamsmendez.starwars.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import com.jamsmendez.starwars.data.model.CharacterFullModel
import com.jamsmendez.starwars.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class DetailViewModel @Inject constructor(
  private val repository: Repository
): ViewModel() {
  val characterModel = MutableLiveData<CharacterFullModel>()

  fun getCharacter(id: Int) {
    viewModelScope.launch {
      val result = repository.getCharacter(id)
      characterModel.postValue(result)
    }
  }
}
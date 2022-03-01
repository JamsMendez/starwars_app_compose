package com.jamsmendez.starwars.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.ExperimentalPagingApi
import androidx.paging.compose.collectAsLazyPagingItems
import com.jamsmendez.starwars.navegation.Screen
import com.jamsmendez.starwars.ui.component.*
import com.jamsmendez.starwars.ui.theme.StarWarsTheme
import com.jamsmendez.starwars.ui.viewmodel.HomeViewModel

@ExperimentalComposeUiApi
@OptIn(ExperimentalPagingApi::class)
@Composable
fun HomeScreen(
  navController: NavHostController,
  homeViewModel: HomeViewModel = hiltViewModel()
) {
  val characters = homeViewModel.getAllCharacters.collectAsLazyPagingItems()

  val searchQuery by homeViewModel.searchQuery
  val hasSearch by homeViewModel.hasSearch
  val searchedCharacters = homeViewModel.searchedCharacters.collectAsLazyPagingItems()

  StarWarsTheme {
    Scaffold {
      Column {
        Header()
        InputSearch(
          searchQuery,
          onTextChange = {
            homeViewModel.updateSearchQuery(it)
          },
          onSearchClicked = {
            homeViewModel.searchCharacters(it)
          },
          onCloseClicked = {
            homeViewModel.setSearch(false)
          }
        )
        ListCharacters2(
          if (hasSearch) searchedCharacters else characters
        ) { id ->
          val route = "${Screen.Detail.route}/${id}"
          navController.navigate(route)
        }
      }
    }
  }
}

@ExperimentalComposeUiApi
@Composable
@Preview(showBackground = true)
fun PreviewHomeScreen() {
  StarWarsTheme {
    Scaffold {
      Column {
        Header()
        //Search()
        //ListCharacters()
      }
    }
  }
}
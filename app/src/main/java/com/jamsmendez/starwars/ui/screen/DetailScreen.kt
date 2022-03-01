package com.jamsmendez.starwars.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.ExperimentalPagingApi
import coil.annotation.ExperimentalCoilApi
import com.jamsmendez.starwars.navegation.Screen
import com.jamsmendez.starwars.ui.component.DetailCharacter
import com.jamsmendez.starwars.ui.theme.StarWarsTheme
import com.jamsmendez.starwars.ui.viewmodel.DetailViewModel

@Composable
fun SearchScreen() {
  val names: List<String> = List(7) { "$it" }
  LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
    items(items = names) { name ->
      Surface(
        color = Color.White,
        modifier = Modifier
          .padding(vertical = 4.dp, horizontal = 8.dp)
      ) {
        Row(modifier = Modifier.padding(24.dp)) {
          Column(
            modifier = Modifier.weight(1f)
          ) {
            Text(text = "Hello")
            Text(text = name)
          }
          Icon(
            imageVector = Icons.Filled.Face,
            contentDescription = "Icon description"
          )
        }
      }
    }
  }
}

@ExperimentalCoilApi
@OptIn(ExperimentalPagingApi::class)
@Composable
fun DetailScreen(
  navController: NavHostController,
  characterId: Int,
  detailViewModel: DetailViewModel = hiltViewModel()
) {
  StarWarsTheme {
    DetailCharacter(detailViewModel) { navController.navigate(Screen.Home.route) }

    LaunchedEffect(Unit) {
      detailViewModel.getCharacter(characterId)
    }
  }
}



package com.jamsmendez.starwars.navegation

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.paging.ExperimentalPagingApi
import coil.annotation.ExperimentalCoilApi
import com.jamsmendez.starwars.ui.screen.DetailScreen
import com.jamsmendez.starwars.ui.screen.HomeScreen

@ExperimentalComposeUiApi
@ExperimentalCoilApi
@ExperimentalPagingApi
@Composable
fun SetupNavGraph(navController: NavHostController) {
  NavHost(
    navController = navController,
    startDestination = Screen.Home.route
  ) {
    composable(route = Screen.Home.route){
      HomeScreen(navController = navController)
    }
    composable(
      route = "${Screen.Detail.route}/{characterId}",
      arguments = listOf(navArgument("characterId") { type = NavType.IntType })
    ){
      it.arguments?.getInt("characterId")?.let { characterId ->
        DetailScreen(
          navController = navController,
          characterId = characterId
        )
      }
    }
  }
}
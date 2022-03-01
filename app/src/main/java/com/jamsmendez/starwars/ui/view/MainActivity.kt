package com.jamsmendez.starwars.ui.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.compose.rememberNavController
import androidx.paging.ExperimentalPagingApi
import coil.annotation.ExperimentalCoilApi
import com.jamsmendez.starwars.navegation.SetupNavGraph
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalComposeUiApi
@AndroidEntryPoint
@OptIn(ExperimentalPagingApi::class)
@ExperimentalPagingApi
class MainActivity : ComponentActivity() {

  @OptIn(ExperimentalCoilApi::class)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      val navController = rememberNavController()
      SetupNavGraph(navController = navController)
    }
  }
}
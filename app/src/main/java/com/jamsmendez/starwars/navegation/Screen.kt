package com.jamsmendez.starwars.navegation

sealed class Screen(val route: String){
  object Home: Screen("home_screen")
  object Detail: Screen("detail_screen")
}
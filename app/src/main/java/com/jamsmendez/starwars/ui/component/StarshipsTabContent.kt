package com.jamsmendez.starwars.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.jamsmendez.starwars.R
import com.jamsmendez.starwars.data.model.StarshipModel

@ExperimentalCoilApi
@Composable
fun StarshipsTabContent(starships: List<StarshipModel>) {
  LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
    items(items = starships) { starship ->
      val painter = rememberImagePainter(data = starship.image) {
        crossfade(durationMillis = 1000)
        error(R.drawable.ic_placeholder_3_2)
        placeholder(R.drawable.ic_placeholder_3_2)
      }

      Card(
        modifier = Modifier.padding(8.dp)
          .fillMaxWidth()
          .wrapContentHeight(),
        shape = MaterialTheme.shapes.medium,
        elevation = 5.dp,
        backgroundColor = MaterialTheme.colors.surface
      ) {
        Row(
          Modifier.background(
            brush = Brush.horizontalGradient(
              colors = listOf(
                Color(0xFF663399),
                Color(0xFF4066E0),
              )
            )
          ),
          verticalAlignment = Alignment.CenterVertically,

          ) {
          Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier.size(120.dp, 80.dp)
              .padding(8.dp),
            contentScale = ContentScale.FillHeight,
          )
          Column(Modifier.padding(8.dp)) {
            Text(
              text = starship.name,
              fontSize = 20.sp,
              style = TextStyle(
                color = Color.White,
              ),
            )
            Row {
              Text(
                text =  "Modelo ",
                style = TextStyle(
                  color = Color.White,
                ),
              )
              Text(
                text = starship.model,
                style = TextStyle(
                  color = Color.White,
                ),
              )
            }
            Row {
              Text(
                text = "Clase ",
                style = TextStyle(
                  color = Color.White,
                ),
              )
              Text(
                text = starship.starshipClass,
                style = TextStyle(
                  color = Color.White,
                ),
              )
            }
          }
        }
      }
    }
  }
}
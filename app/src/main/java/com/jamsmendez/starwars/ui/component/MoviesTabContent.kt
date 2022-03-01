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
import com.jamsmendez.starwars.data.model.FilmModel

@ExperimentalCoilApi
@Composable
fun MoviesTabContent(films: List<FilmModel>) {
  LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
    items(items = films) { film ->
      val painter = rememberImagePainter(data = film.image) {
        crossfade(durationMillis = 1000)
        error(R.drawable.ic_placeholder_8_11)
        placeholder(R.drawable.ic_placeholder_8_11)
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
            modifier = Modifier.size(80.dp, 110.dp)
              .padding(8.dp),
            contentScale = ContentScale.Fit,
          )
          Column(Modifier.padding(8.dp)) {
            Text(
              text = film.title,
              fontSize = 24.sp,
              style = TextStyle(
                color = Color.White,
              ),
            )
            Row {
              Text(
                text = "Episodio ",
                style = TextStyle(
                  color = Color.White,
                ),
              )
              Text(
                text = film.episodeId,
                style = TextStyle(
                  color = Color.White,
                ),
              )
            }
            Row {
              Text(
                text = "Estreno: ",
                style = TextStyle(
                  color = Color.White,
                ),
              )
              Text(
                text = film.releaseDate,
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
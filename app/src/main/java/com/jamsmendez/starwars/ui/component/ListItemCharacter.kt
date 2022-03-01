package com.jamsmendez.starwars.ui.component

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
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
import com.jamsmendez.starwars.data.model.CharacterModel

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ListItemCharacter(
  item: CharacterModel,
  onClick: (id: Int) -> Unit
) {
  val painter = rememberImagePainter(data = item.image) {
    crossfade(durationMillis = 1000)
    error(R.drawable.ic_placeholder_8_11)
    placeholder(R.drawable.ic_placeholder_8_11)
  }

  Card(
    modifier = Modifier.padding(8.dp)
      .fillMaxWidth()
      .wrapContentHeight()
      .clickable { onClick(item.id.toInt()) },
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
          text = item.name,
          fontSize = 24.sp,
          style = TextStyle(
            color = Color.White,
          ),
        )
        Row {
          Text(
            text = "Especie ",
            style = TextStyle(
              color = Color.White,
            ),
          )
          Text(
            text = item.specie.name,
            style = TextStyle(
              color = Color.White,
            ),
          )
        }
      }
    }
  }
}


@OptIn(ExperimentalCoilApi::class)
@Composable
fun ListItemCharacter2(
  item: CharacterModel,
  onClick: (id: Int) -> Unit
) {
  val painter = rememberImagePainter(data = item.image) {
    crossfade(durationMillis = 1000)
    error(R.drawable.ic_placeholder_8_11)
    placeholder(R.drawable.ic_placeholder_8_11)
  }

  Column(
    Modifier
      .fillMaxWidth()
      .fillMaxHeight()
      .padding(8.dp)
  ) {
    Card(
      modifier = Modifier
        .clickable { onClick(item.id.toInt()) }
        .align(Alignment.CenterHorizontally)
        .fillMaxWidth(1f),
      elevation = 0.dp,
      shape = RoundedCornerShape(10.dp)
    ) {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .background(
            brush = Brush.horizontalGradient(
              colors = listOf(
                Color(0xFF663399),
                Color(0xFF4066E0),
              )
            )
          ),
      ) {
        Spacer(modifier = Modifier.height(24.dp))
        Image(
          painter = painter,
          contentDescription = "Image character",
          modifier = Modifier
            .size(140.dp, 157.5f.dp)
            .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
          text = item.name,
          style = TextStyle(
            color = Color.White,
          ),
          modifier = Modifier
            .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(8.dp))
      }
    }
  }
}


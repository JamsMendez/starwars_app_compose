package com.jamsmendez.starwars.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jamsmendez.starwars.R
import com.jamsmendez.starwars.data.model.CharacterFullModel

@Composable
fun DetailTabContent(character: CharacterFullModel) {
  LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
    item {
      Row(modifier = Modifier.padding(8.dp)) {
        Column(
          modifier = Modifier.weight(1f)
        ) {
          Text(text = "Altura")
          Text(text = character.height)
        }
        Icon(
          painter = painterResource(id = R.drawable.baseline_height_24),
          contentDescription = "Icon description"
        )
      }
    }
    item {
      Row(modifier = Modifier.padding(8.dp)) {
        Column(
          modifier = Modifier.weight(1f)
        ) {
          Text(text = "Masa")
          Text(text = character.mass)
        }
        Icon(
          painter = painterResource(id = R.drawable.baseline_scale_24),
          contentDescription = "Icon description"
        )
      }
    }
    item {
    Row(modifier = Modifier.padding(8.dp)) {
      Column(
        modifier = Modifier.weight(1f)
      ) {
        Text(text = "Color de piel")
        Text(text = character.skinColor)
      }
      Icon(
        painter = painterResource(id = R.drawable.baseline_face_24),
        contentDescription = "Icon description"
      )
    }
    }
    item {
      Row(modifier = Modifier.padding(8.dp)) {
        Column(
          modifier = Modifier.weight(1f)
        ) {
          Text(text = "Color de ojo")
          Text(text = character.eyeColor)
        }
        Icon(
          painter = painterResource(id = R.drawable.baseline_visibility_24),
          contentDescription = "Icon description"
        )
      }
    }
    item {
      Row(modifier = Modifier.padding(8.dp)) {
        Column(
          modifier = Modifier.weight(1f)
        ) {
          Text(text = "Genero")
          Text(text = character.gender)
        }
        Icon(
          painter = painterResource(id = R.drawable.baseline_transgender_24),
          contentDescription = "Icon description"
        )

      }
    }
    item {
      Row(modifier = Modifier.padding(8.dp)) {
        Column(
          modifier = Modifier.weight(1f)
        ) {
          Text(text = "Color de pelo")
          Text(text = character.hairColor)
        }
        Icon(
          painter = painterResource(id = R.drawable.baseline_face_retouching_natural_24),
          contentDescription = "Icon description"
        )

      }
    }
    item {
      Row(modifier = Modifier.padding(8.dp)) {
        Column(
          modifier = Modifier.weight(1f)
        ) {
          Text(text = "Año de nacimiento")
          Text(text = character.birthYear)
        }
        Icon(
          painter = painterResource(id = R.drawable.baseline_cake_24),
          contentDescription = "Icon description"
        )
      }
    }
  }
}
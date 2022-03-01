package com.jamsmendez.starwars.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jamsmendez.starwars.R

@Composable
fun Header() {
  Column(
    modifier = Modifier
      .fillMaxWidth()
  ) {
    Spacer(modifier = Modifier.height(8.dp))
    Text(
      text = "STAR\nWARS",
      modifier= Modifier.align(Alignment.CenterHorizontally),
      color = MaterialTheme.colors.primary,
      fontSize = 24.sp,
      fontFamily = FontFamily(
        Font(R.font.sf_distant_galaxy_alternate_italic),
        Font(R.font.sf_distant_galaxy_alternate_italic, FontWeight.Bold)
      )
    )
    Spacer(modifier = Modifier.height(8.dp))
  }
}

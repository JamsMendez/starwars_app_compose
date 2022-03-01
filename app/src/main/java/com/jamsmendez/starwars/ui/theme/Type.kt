package com.jamsmendez.starwars.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.jamsmendez.starwars.R

private val SF = FontFamily(
  Font(R.font.sf_distant_galaxy_alternate_italic),
  Font(R.font.sf_distant_galaxy_alternate_italic, FontWeight.Bold)
)

val SWTypography = Typography(
  h4 = TextStyle(
    fontFamily = SF,
    fontWeight = FontWeight.W600,
    fontSize = 30.sp
  ),
  h5 = TextStyle(
    fontFamily = SF,
    fontWeight = FontWeight.W600,
    fontSize = 24.sp
  ),
  h6 = TextStyle(
    fontFamily = SF,
    fontWeight = FontWeight.W600,
    fontSize = 20.sp
  ),
  subtitle1 = TextStyle(
    fontFamily = SF,
    fontWeight = FontWeight.W600,
    fontSize = 16.sp
  ),
  subtitle2 = TextStyle(
    fontFamily = SF,
    fontWeight = FontWeight.W500,
    fontSize = 14.sp
  ),
  body1 = TextStyle(
    fontFamily = SF,
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp
  ),
  body2 = TextStyle(
    fontFamily = SF,
    fontSize = 14.sp
  ),
  button = TextStyle(
    fontFamily = SF,
    fontWeight = FontWeight.W500,
    fontSize = 14.sp
  ),
  caption = TextStyle(
    fontFamily = SF,
    fontWeight = FontWeight.Normal,
    fontSize = 12.sp
  ),
  overline = TextStyle(
    fontFamily = SF,
    fontWeight = FontWeight.W500,
    fontSize = 12.sp
  )
)
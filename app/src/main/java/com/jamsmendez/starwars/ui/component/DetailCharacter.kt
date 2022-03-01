package com.jamsmendez.starwars.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.paging.ExperimentalPagingApi
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.google.accompanist.pager.*
import com.jamsmendez.starwars.R
import com.jamsmendez.starwars.data.model.CharacterFullModel
import com.jamsmendez.starwars.ui.viewmodel.DetailViewModel
import kotlinx.coroutines.launch

@ExperimentalCoilApi
@ExperimentalPagingApi
@OptIn(ExperimentalPagerApi::class)
@Composable
fun DetailCharacter(
  detailViewModel: DetailViewModel,
  backScreen: () -> Unit
) {
  val character by detailViewModel.characterModel.observeAsState(CharacterFullModel())

  val painter = rememberImagePainter(data = character.image) {
    crossfade(durationMillis = 1000)
    error(R.drawable.ic_placeholder_8_11)
    placeholder(R.drawable.ic_placeholder_8_11)
  }

  val tabs = mutableListOf(
    TabItem(R.drawable.baseline_fact_check_24, "Detalles") {
      DetailTabContent(character = character)
    }
  )

  if (character.films.isNotEmpty()) {
    tabs.add(
      TabItem(R.drawable.baseline_movie_24, "Peliculas") {
        MoviesTabContent(character.films)
      }
    )
  }

  if (character.vehicles.isNotEmpty()) {
    tabs.add(
      TabItem(R.drawable.baseline_rocket_24, "Vehiculos") {
        VehiclesTabContent(character.vehicles)
      }
    )
  }

  if (character.starships.isNotEmpty()) {
    tabs.add(
      TabItem(R.drawable.baseline_rocket_launch_24, "Naves") {
        StarshipsTabContent(character.starships)
      }
    )
  }

  val pagerState = rememberPagerState()

  Scaffold {
    Box(
      modifier = Modifier
        .offset(y = (-200).dp)
        .fillMaxWidth()
        .height(400.dp)
        .clip(
          shape = RoundedCornerShape(percent = 10)
          //.copy(topStart = ZeroCornerSize, topEnd = ZeroCornerSize)
        )
        .background(
          brush = Brush.horizontalGradient(
            colors = listOf(
              Color(0xFF663399),
              Color(0xFF4066E0),
            )
          )
        )
    )
    Column() {
      Row(
        modifier = Modifier
          .wrapContentSize()
      ) {
        ConstraintLayout(
          modifier = Modifier
            .fillMaxWidth()
        ) {
          val (topBarRef, characterRef, nameRef) = createRefs()

          TopAppBar(
            title = {
              Text(
                "Star\nWars",
                modifier = Modifier
                  .fillMaxWidth()
                  .padding(end = 64.dp),
                textAlign = TextAlign.Center,
                fontFamily = FontFamily(
                  Font(R.font.sf_distant_galaxy_alternate_italic),
                  Font(R.font.sf_distant_galaxy_alternate_italic, FontWeight.Bold)
                )
              ) },
            modifier = Modifier
              .background(Color.Transparent)
              .constrainAs(topBarRef) {},
            navigationIcon = {
              IconButton(onClick = { backScreen() }) {
                Icon(Icons.Filled.ArrowBack, contentDescription = null)
              }
            },
            backgroundColor = Color.Transparent,
            elevation = 0.dp,
          )
          Image(
            painter = painter,
            contentDescription = "Character",
            modifier = Modifier
              .size(140.dp, 210.dp)
              .constrainAs(characterRef) {
              top.linkTo(topBarRef.bottom, margin = 20.dp)
              start.linkTo(parent.start)
              end.linkTo(parent.end)
            }
          )
          Text(
            text = character.name,
            modifier = Modifier.constrainAs(nameRef) {
              top.linkTo(characterRef.bottom, margin = 8.dp)
              start.linkTo(parent.start, margin = 16.dp)
              bottom.linkTo(parent.bottom, margin = 8.dp)
            },
            color = Color.Black,
            fontFamily = FontFamily(
              Font(R.font.sf_distant_galaxy_alternate_italic),
              Font(R.font.sf_distant_galaxy_alternate_italic, FontWeight.Bold)
            ),
          )
        }
      }
      Row(
        modifier = Modifier
      ) {
        Column(
          modifier = Modifier.weight(1f)
        ) {
          Tabs(
            tabs = tabs,
            pagerState = pagerState
          )
          TabsContent(
            tabs = tabs,
            pagerState = pagerState,
          )
        }
      }
    }
  }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Tabs(tabs: List<TabItem>, pagerState: PagerState, modifier: Modifier = Modifier) {
  val scope = rememberCoroutineScope()
  TabRow(
    selectedTabIndex = pagerState.currentPage,
    modifier = modifier,
    backgroundColor = Color.White,
    contentColor = Color.Black,
    indicator = { tabPositions ->
      TabRowDefaults.Indicator(
        Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
      )
    }) {
    tabs.forEachIndexed { index, tab ->
      // OR Tab()
      //LeadingIconTab(
      Tab(
        icon = { Icon(painterResource(id = tab.icon), contentDescription = "") },
        text = { Text(
          tab.title,
          style = TextStyle(fontSize = 14.sp)
        ) },
        selected = pagerState.currentPage == index,
        onClick = {
          scope.launch {
            pagerState.animateScrollToPage(index)
          }
        },
      )
    }
  }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabsContent(tabs: List<TabItem>, pagerState: PagerState, modifier: Modifier = Modifier) {
  HorizontalPager(
    state = pagerState,
    count = tabs.size,
    modifier = modifier
  ) { page ->
    tabs[page].screen()
  }
}

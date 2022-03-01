package com.jamsmendez.starwars.ui.component

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.jamsmendez.starwars.data.model.CharacterModel
import kotlinx.coroutines.flow.flowOf

@ExperimentalFoundationApi
fun <T: Any> LazyGridScope.items(
  lazyPagingItems: LazyPagingItems<T>,
  key: ((T) -> Any)?,
  itemContent: @Composable LazyItemScope.(value: T?) -> Unit
) {
  items(lazyPagingItems.itemCount) { index ->
    itemContent(lazyPagingItems[index])
  }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListCharacters(
  items: LazyPagingItems<CharacterModel> = flowOf(
    PagingData.from(
      listOf<CharacterModel>()
    )
  ).collectAsLazyPagingItems(),
  onItemDetail: (id: Int) -> Unit
) {
  LazyVerticalGrid(
    cells = GridCells.Fixed(2),
    contentPadding = PaddingValues(
      horizontal = 16.dp,
      vertical = 8.dp
    )
  ) {
    items(
      lazyPagingItems = items,
      key = { characterModel ->
        characterModel.id
      }
    ) { characterModel ->
      characterModel?.let { ListItemCharacter(item = it, onItemDetail) }
    }
  }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListCharacters2(
  items: LazyPagingItems<CharacterModel>,
  onItemDetail: (id: Int) -> Unit
) {
  //Log.d("Error", items.loadState.toString())
  LazyColumn(
    contentPadding = PaddingValues(
      horizontal = 16.dp,
      vertical = 8.dp
    )
  ) {
    items(
      items = items,
      key = { characterModel ->
        characterModel.id
      }
    ) { characterModel ->
      characterModel?.let { ListItemCharacter(item = it, onItemDetail) }
    }
  }
}

package com.kova700.amazonbookstorepractice.ui.main.search.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kova700.amazonbookstorepractice.ui.main.model.BookItem
import kotlinx.collections.immutable.ImmutableList

@Composable
fun SearchResult(
	lazyGridState: LazyGridState,
	modifier: Modifier = Modifier,
	onItemClick: (Int) -> Unit,
	books: ImmutableList<BookItem>,
) {
	LazyVerticalGrid(
		columns = GridCells.Fixed(2),
		modifier = modifier,
		state = lazyGridState
	) {
		itemsIndexed(
			items = books,
			key = { _, item ->
				item.isbn + item.hashCode()
			},
		) { index, item ->
			SearchResultItem(
				title = item.title,
				thumbnail = item.thumbnail,
				price = item.price,
				modifier = Modifier
					.fillMaxWidth()
					.border(1.dp, Color(0xFFEEEEEE))
					.clickable { onItemClick(index) }
					.padding(15.dp)
			)
		}
	}
}

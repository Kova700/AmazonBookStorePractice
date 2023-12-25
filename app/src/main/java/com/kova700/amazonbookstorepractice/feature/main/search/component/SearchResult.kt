package com.kova700.amazonbookstorepractice.feature.main.search.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kova700.amazonbookstorepractice.data.BookEntity
import kotlinx.collections.immutable.ImmutableList

@Composable
fun SearchResult(
	modifier: Modifier = Modifier,
	onItemClick: (Int) -> Unit = {},
	books: ImmutableList<BookEntity>,
) {
	LazyVerticalGrid(
		columns = GridCells.Fixed(2),
		modifier = modifier
	) {
		itemsIndexed(
			items = books,
			key = { _, item ->
				item.isbn //잡지 같은 경우 ISBN이 같을 수 있음 (추후 수정)
			},
		) { index, item ->
			SearchResultItem(
				title = item.title,
				thumbnail = item.thumbnail,
				price = item.price,
				modifier = Modifier
					.fillMaxWidth()
					.border(1.dp, Color.Gray)
					.clickable { onItemClick(index) }
					.padding(15.dp)
			)
		}
	}
}

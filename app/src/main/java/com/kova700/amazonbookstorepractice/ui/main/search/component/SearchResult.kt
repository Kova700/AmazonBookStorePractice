package com.kova700.amazonbookstorepractice.ui.main.search.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kova700.amazonbookstorepractice.ui.main.model.BookItem
import kotlinx.collections.immutable.ImmutableList

@Composable
fun SearchResult(
	lazyListState: LazyListState,
	onItemClick: (Int) -> Unit,
	books: ImmutableList<BookItem>,
) {
	val columnCount = 2

	LazyColumn(
		modifier = Modifier.fillMaxSize(),
		state = lazyListState
	) {
		items(
			count = (books.size + 1) / columnCount,
			key = { index -> books[index].isbn + books[index].hashCode() }
		) { rowIndex ->

			Row(modifier = Modifier.height(IntrinsicSize.Max)) {
				for (i in 0 until columnCount) {
					val itemIndex = rowIndex * columnCount + i

					if (itemIndex < books.size) {
						SearchResultItem(
							title = books[itemIndex].title,
							thumbnail = books[itemIndex].thumbnail,
							price = books[itemIndex].price,
							modifier = Modifier
								.fillMaxHeight()
								.weight(1f)
								.border(1.dp, Color(0xFFEEEEEE))
								.clickable { onItemClick(itemIndex) }
								.padding(15.dp)
						)
					} else {
						Spacer(Modifier.weight(1f))
					}
				}

			}

		}
	}
}

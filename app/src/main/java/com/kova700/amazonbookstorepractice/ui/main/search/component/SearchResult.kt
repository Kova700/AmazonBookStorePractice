package com.kova700.amazonbookstorepractice.ui.main.search.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
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

			var expandedState by remember { mutableStateOf(false) }
			var expandedIndex by remember { mutableIntStateOf(-1) }

			Row(
				modifier = Modifier
					.height(IntrinsicSize.Max)
			) {
				for (i in 0 until columnCount) {
					val itemIndex = rowIndex * columnCount + i

					if (itemIndex < books.size) {
						SearchResultItem(
							title = books[itemIndex].title,
							thumbnail = books[itemIndex].thumbnail,
							price = books[itemIndex].price,
							expandedState = (expandedIndex == itemIndex) && expandedState,
							modifier = Modifier
								.fillMaxHeight()
								.weight(1f)
								.border(1.dp, Color(0xFFEEEEEE))
								.clickable {
									expandedState = !expandedState
									expandedIndex = itemIndex
								}
								.padding(15.dp)
						)
					} else {
						Spacer(Modifier.weight(1f))
					}
				}
			}

			AnimatedVisibility(visible = expandedState) {
				ExpandedContent(
					expandedIndex = expandedIndex,
					onItemClick = onItemClick,
					bookItem = books[expandedIndex]
				)
			}

		}
	}
}

@Composable
fun ExpandedContent(
	expandedIndex: Int,
	onItemClick: (Int) -> Unit,
	bookItem: BookItem
) {

	Column(
		modifier = Modifier
			.fillMaxWidth()
			.clickable {
				onItemClick(expandedIndex)
			}
			.padding(
				horizontal = 25.dp,
				vertical = 20.dp
			)
	) {
		Text(
			modifier = Modifier
				.fillMaxWidth(),
			text = bookItem.title,
			textAlign = TextAlign.Center
		)

		Spacer(modifier = Modifier.height(20.dp))

		Row {

			Box(modifier = Modifier.weight(1F)) {
				SubcomposeAsyncImage(
					model = bookItem.thumbnail,
					contentDescription = "bookThumbnail",
					modifier = Modifier
						.width(90.dp)
						.height(130.dp)
						.clip(RoundedCornerShape(6.dp)),
					contentScale = ContentScale.Fit,
					loading = { CircularProgressIndicator() },
				)
			}

			Spacer(modifier = Modifier.width(20.dp))

			Text(
				modifier = Modifier.weight(1F),
				text = "${bookItem.price} 원",
				color = Color(0xFFA44B38),
				fontSize = 17.sp
			)
		}

	}
}

@Composable
@Preview(showBackground = true)
fun ExpandedContentPreview() {
	ExpandedContent(
		expandedIndex = 0,
		onItemClick = {},
		bookItem = BookItem.Default.copy(
			title = "테스트 책 제목"
		)
	)
}
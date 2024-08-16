package com.kova700.search.feature.search.component.searchresult

import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.kova700.search.feature.model.BookItem
import kotlinx.collections.immutable.ImmutableList

const val COLUMN_COUNT = 2

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun SearchResult(
	lazyListState: LazyListState,
	onItemClick: (Int) -> Unit,
	books: ImmutableList<BookItem>,
	onItemExpend: (Int) -> Unit,
) {

	/**
	 * 하단 애니메이션 사용과 행간 텍스트 높이 통일을 위해 GridLazyColumn에서 LazyColumn로 변경
	 */
	LazyColumn(
		modifier = Modifier.fillMaxSize(),
		state = lazyListState
	) {

		items(
			count = (books.size + 1) / COLUMN_COUNT,
			key = { rowIndex ->
				val leftItemIndex = rowIndex * COLUMN_COUNT
				if (leftItemIndex == books.lastIndex) books[leftItemIndex].isbn
				else books[leftItemIndex].isbn + books[leftItemIndex + 1].isbn
			}
		) { rowIndex ->
			val leftItemIndex = rowIndex * COLUMN_COUNT
			val expandedIndex = when {
				books[leftItemIndex].isExpanded -> leftItemIndex
				(leftItemIndex != books.lastIndex) && books[leftItemIndex + 1].isExpanded -> leftItemIndex + 1
				else -> null
			}

			Row(
				modifier = Modifier
					.height(IntrinsicSize.Max)
					.animateItemPlacement()
			) {
				for (i in 0 until COLUMN_COUNT) {
					val itemIndex = rowIndex * COLUMN_COUNT + i

					if (itemIndex < books.size) {
						SearchResultItem(
							title = books[itemIndex].title,
							thumbnail = books[itemIndex].thumbnail,
							price = books[itemIndex].price,
							expandedState = (expandedIndex == itemIndex),
							modifier = Modifier
								.fillMaxHeight()
								.weight(1f)
								.border(1.dp, Color(0xFFEEEEEE))
								.clickable { onItemExpend(itemIndex) }
								.padding(15.dp)
						)
					} else {
						Spacer(Modifier.weight(1f))
					}
				}
			}

			if (expandedIndex != null) {
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
private fun ExpandedContent(
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
private fun ExpandedContentPreview() {
	ExpandedContent(
		expandedIndex = 0,
		onItemClick = {},
		bookItem = BookItem.Default.copy(
			title = "테스트 책 제목"
		)
	)
}
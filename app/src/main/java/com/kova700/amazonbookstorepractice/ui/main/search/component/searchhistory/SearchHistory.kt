package com.kova700.amazonbookstorepractice.ui.main.search.component.searchhistory

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun SearchHistory(
	historyList: ImmutableList<String>,
	onHistoryClick: (String) -> Unit,
	onHistoryRemoveClick: (Int) -> Unit,
	onHistoryClearClick: () -> Unit,
) {
	val focusManager = LocalFocusManager.current

	Column {

		Spacer(modifier = Modifier.height(10.dp))

		LazyColumn {
			item {

				Row(
					modifier = Modifier
						.fillMaxWidth()
						.padding(horizontal = 20.dp),
					horizontalArrangement = Arrangement.Absolute.SpaceBetween,
					verticalAlignment = Alignment.CenterVertically,
				) {
					Text(
						text = "최근 검색어",
						fontSize = 12.sp,
						fontWeight = FontWeight.Bold
					)
					Text(
						text = "전체삭제",
						fontSize = 12.sp,
						fontWeight = FontWeight.Bold,
						modifier = Modifier
							.clickable { onHistoryClearClick() }
							.padding(5.dp)
					)
				}
			}

			itemsIndexed(
				items = historyList,
//				key = { _, item -> item },
			) { index, historyString ->

				SearchHistoryItem(
					historyString = historyString,
					onHistoryClick = onHistoryClick,
					onHistoryRemoveClick = { onHistoryRemoveClick(index) },
					focusManager = focusManager
				)

			}
		}
	}
}

@Preview(showBackground = true)
@Composable
fun SearchHistoryPreview() {
	SearchHistory(
		historyList = persistentListOf("이것이123124", "검색기록", "테스트123"),
		onHistoryClick = {},
		onHistoryRemoveClick = {},
		onHistoryClearClick = {},
	)
}
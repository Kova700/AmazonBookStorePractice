package com.kova700.amazonbookstorepractice.ui.main.search.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kova700.amazonbookstorepractice.R
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun SearchHistory(
	historyList: ImmutableList<String>,
	onHistoryClick: (String) -> Unit
) {
	Column {

		Spacer(modifier = Modifier.height(10.dp))

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
				fontWeight = FontWeight.Bold
			)
		}

		Spacer(modifier = Modifier.height(10.dp))

		LazyColumn {
			items(historyList) { historyString ->
				Row(
					modifier = Modifier
						.fillMaxWidth()
						.padding(horizontal = 20.dp),
					horizontalArrangement = Arrangement.Absolute.SpaceBetween,
					verticalAlignment = Alignment.CenterVertically,
				) {

					Text(
						modifier = Modifier.clickable { onHistoryClick(historyString) },
						text = historyString,
						fontSize = 14.sp
					)

					IconButton(
						onClick = {} //삭제 API
					) {
						Icon(
							imageVector = ImageVector.vectorResource(id = R.drawable.ic_delete),
							contentDescription = null,
							tint = Color.Unspecified
						)
					}
				}
			}
		}
	}

}

@Preview(showBackground = true)
@Composable
fun SearchHistoryPreview() {
	SearchHistory(
		historyList = persistentListOf("이것이123124", "검색기록", "테스트123"),
		onHistoryClick = {}
	)
}
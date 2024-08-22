package com.kova700.feature.searchhistory

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kova700.amazonbookstorepractice.core.design_system.R

@Composable
internal fun SearchHistoryItem(
	historyString: String,
	onHistoryClick: () -> Unit,
	onHistoryRemoveClick: () -> Unit,
	focusManager: FocusManager? = null
) {
	Row(
		modifier = Modifier
			.fillMaxWidth()
			.padding(horizontal = 20.dp)
			.clickable {
				onHistoryClick()
				focusManager?.clearFocus()
			},
		horizontalArrangement = Arrangement.Absolute.SpaceBetween,
		verticalAlignment = Alignment.CenterVertically,
	) {

		Text(
			modifier = Modifier
				.weight(1F),
			text = historyString,
			fontSize = 14.sp
		)

		IconButton(onClick = onHistoryRemoveClick) {
			Icon(
				imageVector = ImageVector.vectorResource(id = R.drawable.ic_delete),
				contentDescription = null,
				tint = Color.Unspecified
			)
		}
	}
}

@Preview(showBackground = true)
@Composable
private fun SearchHistoryItemPreview() {
	SearchHistoryItem(
		historyString = "테스트 아이템 1",
		onHistoryClick = {},
		onHistoryRemoveClick = {},
	)
}
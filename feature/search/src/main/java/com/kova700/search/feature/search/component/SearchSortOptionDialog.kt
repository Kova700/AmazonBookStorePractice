package com.kova700.amazonbookstorepractice.ui.main.search.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.kova700.booksearch.model.KakaoBookSearchSortType

@Composable
internal fun SearchSortOptionDialog(
	onChangeState: () -> Unit,
	onSortOptionChange: (KakaoBookSearchSortType) -> Unit,
	currentSortType: KakaoBookSearchSortType
) {
	Dialog(onDismissRequest = onChangeState) {

		Surface(
			modifier = Modifier
				.width(200.dp)
				.wrapContentHeight(),
			shape = RoundedCornerShape(12.dp),
			color = Color.White
		) {
			Column(
				modifier = Modifier
					.padding(horizontal = 15.dp)
			) {

				Spacer(
					modifier = Modifier
						.height(25.dp)
						.fillMaxWidth()
				)

				Text(
					modifier = Modifier.fillMaxWidth(),
					textAlign = TextAlign.Left,
					text = "검색 옵션",
					fontSize = 15.sp,
					fontWeight = FontWeight.Bold
				)

				Spacer(
					modifier = Modifier
						.height(25.dp)
						.fillMaxWidth()
				)

				Text(
					modifier = Modifier
						.fillMaxWidth()
						.clickable { onSortOptionChange(KakaoBookSearchSortType.ACCURACY) }
						.padding(5.dp),
					textAlign = TextAlign.Left,
					text = "정확도순",
					color = if (currentSortType == KakaoBookSearchSortType.ACCURACY) Color(0xFF5648FF) else Color.Black,
					fontSize = 14.sp
				)

				Spacer(
					modifier = Modifier
						.height(25.dp)
						.fillMaxWidth()
				)

				Text(
					modifier = Modifier
						.fillMaxWidth()
						.clickable { onSortOptionChange(KakaoBookSearchSortType.LATEST) }
						.padding(5.dp),
					textAlign = TextAlign.Left,
					text = "최신순",
					color = if (currentSortType == KakaoBookSearchSortType.LATEST) Color(0xFF5648FF) else Color.Black,
					fontSize = 14.sp
				)

				Spacer(
					modifier = Modifier
						.height(25.dp)
						.fillMaxWidth()
				)

			}

		}
	}
}

@Preview(showBackground = true)
@Composable
private fun SearchOptionDialogPreview() {
	SearchSortOptionDialog(
		onChangeState = {},
		onSortOptionChange = {},
		currentSortType = KakaoBookSearchSortType.ACCURACY
	)
}
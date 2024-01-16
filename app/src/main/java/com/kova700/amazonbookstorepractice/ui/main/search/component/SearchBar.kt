package com.kova700.amazonbookstorepractice.ui.main.search.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kova700.amazonbookstorepractice.R

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(
	searchKeyword: String,
	onValueChange: (String) -> Unit,
	onSearchClick: () -> Unit,
	onKeywordClear: () -> Unit,
	onOptionClick: () -> Unit,
) {

	//TODO : 아래에 검색기록도 추가

	val keyboardController = LocalSoftwareKeyboardController.current

	Card(
		modifier = Modifier
			.fillMaxWidth()
			.wrapContentHeight(Alignment.CenterVertically)
			.padding(10.dp),
		colors = CardDefaults.cardColors(
			containerColor = Color.White
		),
		shape = RoundedCornerShape(50.dp),
		elevation = CardDefaults.cardElevation(
			defaultElevation = 5.dp
		)
	) {

		Row(
			modifier = Modifier.fillMaxWidth(),
			horizontalArrangement = Arrangement.Center,
			verticalAlignment = Alignment.CenterVertically
		) {
			Spacer(modifier = Modifier.width(10.dp))

			IconButton(onClick = onSearchClick) {
				Icon(
					imageVector = ImageVector.vectorResource(id = R.drawable.ic_searcn_image_search),
					contentDescription = null,
					tint = Color.Unspecified
				)
			}

			TextField(
				value = searchKeyword,
				onValueChange = onValueChange,
				placeholder = { Text("검색할 도서명을 입력해주세요.") },
				modifier = Modifier.weight(1f),
				singleLine = true,
				colors = TextFieldDefaults.textFieldColors(
					containerColor = Color.White,
					disabledIndicatorColor = Color.Transparent,
					focusedIndicatorColor = Color.Transparent,
					unfocusedIndicatorColor = Color.Transparent
				),
				keyboardActions = KeyboardActions(
					onDone = {
						onSearchClick()
						keyboardController?.hide()
					}
				)
			)

			if (searchKeyword.isNotBlank()) {
				IconButton(onClick = onKeywordClear) {
					Icon(
						imageVector = ImageVector.vectorResource(id = R.drawable.ic_text_delete_image_search),
						contentDescription = null,
						tint = Color.Unspecified
					)
				}
			}

			IconButton(onClick = onOptionClick) {
				Icon(
					imageVector = ImageVector.vectorResource(id = R.drawable.ic_search_option),
					contentDescription = null,
					tint = Color.Unspecified
				)
			}

			Spacer(modifier = Modifier.width(10.dp))
		}
	}

}

@Preview(showBackground = true)
@Composable
fun SearchBarPreview() {
	SearchBar(
		searchKeyword = "",
		onValueChange = {},
		onSearchClick = {},
		onKeywordClear = {},
		onOptionClick = {}
	)
}
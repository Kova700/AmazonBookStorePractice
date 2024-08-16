package com.kova700.search.feature.search.component

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kova700.amazonbookstorepractice.core.design_system.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SearchBar(
	searchKeyword: String,
	onValueChange: (String) -> Unit,
	onTextFieldFocus: () -> Unit,
	onSearchClick: () -> Unit,
	onKeywordClear: () -> Unit,
	onOptionClick: () -> Unit,
) {
	val focusManager = LocalFocusManager.current

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

			IconButton(onClick = {
				onSearchClick()
				focusManager.clearFocus()
			}) {
				Icon(
					imageVector = ImageVector.vectorResource(id = R.drawable.ic_searcn_image_search),
					contentDescription = null,
					tint = Color.Unspecified
				)
			}

			TextField(
				modifier = Modifier
					.weight(1f)
					.onFocusEvent { focusState ->
						if (focusState.hasFocus) onTextFieldFocus()
					},
				value = searchKeyword,
				onValueChange = onValueChange,
				placeholder = {
					Text(
						text = "검색할 도서명을 입력해주세요.",
						fontSize = 14.sp
					)
				},
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
						focusManager.clearFocus()
					}
				),
			)

			if (searchKeyword.isNotBlank()) {
				IconButton(onClick = {
					onKeywordClear()
					onTextFieldFocus()
				}) {
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
private fun SearchBarPreview() {
	SearchBar(
		searchKeyword = "",
		onValueChange = {},
		onTextFieldFocus = {},
		onSearchClick = {},
		onKeywordClear = {},
		onOptionClick = {}
	)
}
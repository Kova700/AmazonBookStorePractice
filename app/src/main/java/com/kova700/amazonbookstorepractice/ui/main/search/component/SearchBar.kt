package com.kova700.amazonbookstorepractice.ui.main.search.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
	searchKeyword: String,
	onValueChange: (String) -> Unit,
	onSearchClick: () -> Unit,
) {

	//TODO : 디자인에 맞게 적당히 커스텀해야함
	//TODO : 아래에 검색기록도 추가
	Row(
		modifier = Modifier.fillMaxWidth(),
	) {
		OutlinedTextField(
			value = searchKeyword,
			onValueChange = onValueChange,
			placeholder = { Text("검색할 도서명을 입력해주세요.") },
			modifier = Modifier.weight(1f),
			singleLine = true
		)
		IconButton(onClick = onSearchClick) {
			Icon(Icons.Filled.Search, contentDescription = null)
		}
	}

}
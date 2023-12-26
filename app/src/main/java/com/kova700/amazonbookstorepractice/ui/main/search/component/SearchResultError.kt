package com.kova700.amazonbookstorepractice.ui.main.search.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SearchResultError(
	modifier: Modifier = Modifier.fillMaxSize(),
) {
	Box(modifier = modifier) {
		Text(text = "네트워크 실패")
	}
}

@Preview(showBackground = true)
@Composable
fun PreviewError() {
	SearchResultError()
}
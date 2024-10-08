package com.kova700.search.feature.search.component.searchresult

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
internal fun SearchResultLoading(
	modifier: Modifier = Modifier.fillMaxSize(),
) {
	Box(modifier = modifier) {
		CircularProgressIndicator(
			modifier = Modifier.align(Alignment.Center),
			color = Color.Blue
		)
	}
}

@Composable
@Preview(showBackground = true)
private fun Preview() {
	SearchResultLoading()
}
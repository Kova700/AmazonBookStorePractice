package com.kova700.search.feature.detail

import android.webkit.WebView
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun DetailWebView(url: String) {
	AndroidView(factory = {
		WebView(it).apply {
			loadUrl(url)
		}
	})
}
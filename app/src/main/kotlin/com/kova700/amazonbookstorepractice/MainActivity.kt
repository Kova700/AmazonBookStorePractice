package com.kova700.amazonbookstorepractice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.kova700.search.feature.MainScreen
import com.kova700.design_system.theme.AmazonBookStorePracticeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			AmazonBookStorePracticeTheme {
				MainScreen()
			}
		}
	}
}

@Preview(showBackground = true)
@Composable
fun BookStorePreview() {
	AmazonBookStorePracticeTheme {
		MainScreen()
	}
}
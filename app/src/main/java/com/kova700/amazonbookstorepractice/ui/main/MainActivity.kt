package com.kova700.amazonbookstorepractice.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.kova700.amazonbookstorepractice.ui.theme.AmazonBookStorePracticeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			AmazonBookStorePracticeTheme {
				MainScreen(
					navController = rememberNavController(),
				)
			}
		}
	}
}

@Preview(showBackground = true)
@Composable
fun BookStorePreview() {
	AmazonBookStorePracticeTheme {
		MainScreen(
			navController = rememberNavController(),
		)
	}
}
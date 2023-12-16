package com.kova700.amazonbookstorepractice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kova700.amazonbookstorepractice.ui.theme.AmazonBookStorePracticeTheme

//TODO : 상단에 검색창
//TODO : 검색결과 LazyColumn으로 노출
//TODO : 아이템 누르면 Detail화면 노출
//TODO : Detail화면에서 자세히 보기 누르면 WebView 노출
//TODO : 도서 검색 API = 카카오 도서
class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			AmazonBookStorePracticeTheme {
				// A surface container using the 'background' color from the theme
				Surface(
					modifier = Modifier.fillMaxSize(),
					color = MaterialTheme.colorScheme.background
				) {
					Greeting("Android")
				}
			}
		}
	}
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
	Text(
		text = "Hello $name!",
		modifier = modifier
	)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
	AmazonBookStorePracticeTheme {
		Greeting("Android")
	}
}
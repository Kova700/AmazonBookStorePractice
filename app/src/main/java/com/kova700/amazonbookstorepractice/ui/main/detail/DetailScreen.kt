package com.kova700.amazonbookstorepractice.ui.main.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun DetailScreen(
	detailViewModel: DetailViewModel = hiltViewModel(),
	navigateToWebView: (String) -> Unit = {}
) {
	val book by detailViewModel.viewState.collectAsStateWithLifecycle()

	DetailScreen(
		title = book.title,
		thumbnail = book.thumbnail,
		authors = book.authors,
		price = book.price,
		contents = book.contents,
		datetime = book.datetime,
		isbn = book.isbn,
		publisher = book.publisher,
		status = book.status,
		translators = book.translators,
		url = book.url,
		navigateToWebView = navigateToWebView
	)
}

@Composable
fun DetailScreen(
	title: String,
	thumbnail: String,
	authors: ImmutableList<String>,
	price: Int,
	contents: String,
	datetime: String,
	isbn: String,
	publisher: String,
	status: String,
	translators: ImmutableList<String>,
	url: String,
	navigateToWebView: (String) -> Unit = {}
) {
	val scrollState = rememberScrollState()

	Column(
		modifier = Modifier
			.padding(
				top = 30.dp,
				start = 15.dp,
				end = 15.dp
			)
			.fillMaxSize()
			.verticalScroll(scrollState),
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Row(
			modifier = Modifier
				.fillMaxWidth()
				.padding(
					top = 15.dp,
					bottom = 15.dp
				)
		) {
			AsyncImage(
				model = thumbnail,
				contentDescription = "bookThumbnail",
				modifier = Modifier
					.width(120.dp)
					.height(170.dp)
					.clip(RoundedCornerShape(10.dp)),
				contentScale = ContentScale.Fit
			)

			Spacer(modifier = Modifier.width(15.dp))

			Column(
				modifier = Modifier.fillMaxWidth(),
				verticalArrangement = Arrangement.spacedBy(5.dp)
			) {
				Text(
					text = authors.joinToString(" , "),
					color = Color(0xFF428D83)
				)
				Text(
					text = title,
					textAlign = TextAlign.Start
				)
				Text(text = "출간일 : $datetime")
				Text(text = "isbn : $isbn")
				Text(text = "상태 : $status")
				Text(text = "translators : ${translators.joinToString(" , ")}")
				Text(text = "출판사 : $publisher")

			}
		}

		Text(
			text = "$price 원",
			fontSize = 25.sp,
			color = Color(0xFFA44B38)
		)

		Spacer(modifier = Modifier.height(30.dp))

		Text(
			text = "줄거리",
			fontSize = 20.sp,
			color = Color.Gray
		)

		Spacer(modifier = Modifier.height(20.dp))

		Text(text = contents)

		Spacer(modifier = Modifier.height(20.dp))

		Button(
			onClick = { navigateToWebView(url) }
		) {
			Text(text = "자세히 보기")
		}
	}
}

@Preview(showBackground = true)
@Composable
fun PreviewDetail() {
	DetailScreen(
		title = "책 제목 블라블라블라블라",
		thumbnail = "https://search1.kakaocdn.net/thumb/R120x174.q85/?fname=http%3A%2F%2Ft1.daumcdn.net%2Flbook%2Fimage%2F6468627%3Ftimestamp%3D20231116183409",
		authors = persistentListOf("작가이름은", "마바사"),
		price = 30600,
		datetime = "2021-10-02",
		isbn = "12345648-154648454",
		status = "정상 판매",
		translators = persistentListOf("ABC", "CSV"),
		url = "https://search.daum.net/search?w=bookpage&bookId=4851688&q=%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C+with+Kotlin+%EC%95%B1+%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D+%EA%B0%80%EC%9D%B4%EB%93%9C",
		contents = "최신 버전에 맞춰 완벽하게 실습할 수 있는 코틀린 안드로이드 앱 개발 도서! 코틀린, 안드로이드, 안드로이드 스튜디오! 코틀린으로 안드로이드 앱을 개발하려면 살펴봐야 할 사항이 많습니다. 특히 코틀린 1.4.20 버전부터는 코틀린 익스텐션이 폐기되는 터라 입문자로서 더욱 혼란스럽습니다. 〈이것이 안드로이드다 with 코틀린(개정판)〉은 최신 버전에 대응해 코틀린 익스텐션 코드를 걷어내고 뷰바인딩 코드로 전면 수정했습니다. 물론 안드로이드 스튜디오 4.1",
		publisher = "블라블라 출판사"
	)
}
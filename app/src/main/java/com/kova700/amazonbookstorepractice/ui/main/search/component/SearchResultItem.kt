package com.kova700.amazonbookstorepractice.ui.main.search.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage

@Composable
fun SearchResultItem(
	title: String,
	thumbnail: String,
	price: Int,
	expandedState :Boolean,
	modifier: Modifier = Modifier
) {
	Box(
		contentAlignment = Alignment.Center,
		modifier = modifier
	) {
		Column(
			verticalArrangement = Arrangement.Center,
			horizontalAlignment = Alignment.CenterHorizontally
		) {

			SubcomposeAsyncImage(
				model = thumbnail,
				contentDescription = "bookThumbnail",
				modifier = Modifier
					.width(90.dp)
					.height(130.dp)
					.clip(RoundedCornerShape(6.dp)),
				contentScale = ContentScale.Fit,
				loading = { CircularProgressIndicator() },
			)

			Spacer(modifier = Modifier.height(15.dp))

			if (expandedState.not()){
				Text(
					text = title,
					fontSize = 17.sp,
					textAlign = TextAlign.Center,
					maxLines = 3
				)

				Text(
					text = "$price 원",
					color = Color(0xFFA44B38),
					fontSize = 17.sp
				)
			}

		}
	}
}

@Preview(showBackground = true)
@Composable
fun PreviewResultItem() {
	SearchResultItem(
		title = "블라블라",
		thumbnail = "https://search1.kakaocdn.net/thumb/R120x174.q85/?fname=http%3A%2F%2Ft1.daumcdn.net%2Flbook%2Fimage%2F6468627%3Ftimestamp%3D20231116183409",
		price = 30600,
		expandedState = true
	)
}
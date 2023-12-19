package com.kova700.amazonbookstorepractice.data.api

import com.kova700.amazonbookstorepractice.BuildConfig
import com.kova700.amazonbookstorepractice.data.BookResponse
import com.kova700.amazonbookstorepractice.feature.main.search.KakaoBookSearchSortType
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.parameter
import io.ktor.http.HttpHeaders
import javax.inject.Inject

class KtorBookSearchService @Inject constructor(
	private val ktorHttpClient: HttpClient
) : BookSearchService {

	override suspend fun searchBooks(
		query: String,
		sort: KakaoBookSearchSortType,
		page: Int,
		size: Int
	): BookResponse {
		return ktorHttpClient.get(SEARCH_BOOKS_URL) {
			headers {
				append(HttpHeaders.Authorization, BuildConfig.KAKAO_REST_API_KEY)
			}
			parameter("query", query)
			parameter("sort", sort)
			parameter("page", page)
			parameter("size", size)
		}.body<BookResponse>()
	}

	companion object {
		const val SEARCH_BOOKS_URL = "search/book"
	}

}
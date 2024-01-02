package com.kova700.amazonbookstorepractice.data.api

import com.kova700.amazonbookstorepractice.data.response.BookResponse
import com.kova700.amazonbookstorepractice.domain.model.KakaoBookSearchSortType
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
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
package com.kova700.core.network.booksearch

import com.kova700.amazonbookstorepractice.core.network.booksearch.BuildConfig
import com.kova700.core.network.booksearch.model.BookResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import javax.inject.Inject

internal class KtorBookSearchService @Inject constructor(
	baseHttpClient: HttpClient,
) : BookSearchNetworkService {

	private val kakaoSearchClient = baseHttpClient.config {
		install(DefaultRequest) {
			header(HttpHeaders.Authorization, BuildConfig.KAKAO_REST_API_KEY)
		}

		defaultRequest {
			url {
				protocol = URLProtocol.HTTPS
				host = KAKAO_BASE_URL
			}
		}
	}

	override suspend fun searchBooks(
		query: String,
		sort: String,
		page: Int,
		size: Int,
	): BookResponse {

		return kakaoSearchClient.get(SEARCH_BOOKS_URL) {
			parameter("query", query)
			parameter("sort", sort)
			parameter("page", page)
			parameter("size", size)
		}.body<BookResponse>()
	}

	companion object {
		private const val KAKAO_BASE_URL = "dapi.kakao.com/v3"
		const val SEARCH_BOOKS_URL = "search/book"
	}
}
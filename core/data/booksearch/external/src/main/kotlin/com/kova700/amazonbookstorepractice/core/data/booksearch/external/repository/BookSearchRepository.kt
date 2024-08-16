package com.kova700.amazonbookstorepractice.core.data.booksearch.external.repository

import com.kova700.amazonbookstorepractice.core.data.booksearch.external.model.Book
import com.kova700.amazonbookstorepractice.core.data.booksearch.external.model.KakaoBookSearchSortType
import kotlinx.coroutines.flow.Flow

interface BookSearchRepository {

	suspend fun getSearchResult(
		query: String,
		sort: KakaoBookSearchSortType,
	): List<Book>

	fun getSearchResultFlow(): Flow<List<Book>>
	fun getCachedBook(index: Int): Book
}
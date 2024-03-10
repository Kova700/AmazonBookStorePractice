package com.kova700.amazonbookstorepractice.domain.repository

import com.kova700.amazonbookstorepractice.domain.model.Book
import com.kova700.amazonbookstorepractice.domain.model.KakaoBookSearchSortType
import kotlinx.coroutines.flow.Flow

interface BookSearchRepository {

	suspend fun getSearchResult(
		query: String,
		sort: KakaoBookSearchSortType,
	): List<Book>

	fun getSearchResultFlow(): Flow<List<Book>>

	fun getCachedBook(index: Int): Book
}
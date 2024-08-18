package com.kova700.core.network.booksearch

import com.kova700.amazonbookstorepractice.core.data.booksearch.external.model.KakaoBookSearchSortType
import com.kova700.core.network.booksearch.model.BookResponse

interface BookSearchNetworkService {

	suspend fun searchBooks(
		query: String,
		sort: KakaoBookSearchSortType,
		page: Int,
		size: Int,
	): BookResponse

}
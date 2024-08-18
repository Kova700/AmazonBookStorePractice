package com.kova700.core.network.booksearch

import com.kova700.core.network.booksearch.model.BookResponse

interface BookSearchNetworkService {

	suspend fun searchBooks(
		query: String,
		sort: String,
		page: Int,
		size: Int,
	): BookResponse

}
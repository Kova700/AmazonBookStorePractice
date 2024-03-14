package com.kova700.booksearch.api

import com.kova700.booksearch.model.BookResponse

internal interface BookSearchService {

	suspend fun searchBooks(
		query: String,
		sort: String,
		page: Int,
		size: Int
	): BookResponse

}
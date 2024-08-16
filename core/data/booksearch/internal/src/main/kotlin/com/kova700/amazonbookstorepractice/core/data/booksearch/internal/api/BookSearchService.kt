package com.kova700.amazonbookstorepractice.core.data.booksearch.internal.api

import com.kova700.amazonbookstorepractice.core.data.booksearch.internal.model.BookResponse

internal interface BookSearchService {

	suspend fun searchBooks(
		query: String,
		sort: String,
		page: Int,
		size: Int
	): BookResponse

}
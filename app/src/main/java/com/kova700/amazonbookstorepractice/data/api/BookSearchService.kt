package com.kova700.amazonbookstorepractice.data.api

import com.kova700.amazonbookstorepractice.data.response.BookResponse

interface BookSearchService {

	suspend fun searchBooks(
		query: String,
		sort: String,
		page: Int,
		size: Int
	): BookResponse

}
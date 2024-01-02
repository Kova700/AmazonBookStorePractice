package com.kova700.amazonbookstorepractice.data.api

import com.kova700.amazonbookstorepractice.data.response.BookResponse
import com.kova700.amazonbookstorepractice.domain.model.KakaoBookSearchSortType

interface BookSearchService {

	suspend fun searchBooks(
		query: String,
		sort: KakaoBookSearchSortType,
		page: Int,
		size: Int
	): BookResponse

}
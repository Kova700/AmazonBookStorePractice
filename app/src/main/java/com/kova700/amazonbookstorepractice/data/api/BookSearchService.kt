package com.kova700.amazonbookstorepractice.data.api

import com.kova700.amazonbookstorepractice.data.BookResponse
import com.kova700.amazonbookstorepractice.feature.main.search.KakaoBookSearchSortType

interface BookSearchService {

	suspend fun searchBooks(
		query: String,
		sort: KakaoBookSearchSortType,
		page: Int,
		size: Int
	) : BookResponse

}
package com.kova700.amazonbookstorepractice.domain.repository

import com.kova700.amazonbookstorepractice.domain.model.Book
import com.kova700.amazonbookstorepractice.domain.model.KakaoBookSearchSortType

interface BookSearchRepository {

	suspend fun loadSearchData(
		query: String,
		sort: KakaoBookSearchSortType = KakaoBookSearchSortType.ACCURACY,
		page: Int = FIRST_PAGE,
		size: Int = DEFAULT_PAGING_SIZE
	): List<Book>

	fun getBook(index: Int): Book

	companion object {
		const val FIRST_PAGE = 1
		const val DEFAULT_PAGING_SIZE = 20
	}
}
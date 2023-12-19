package com.kova700.amazonbookstorepractice.data

import com.kova700.amazonbookstorepractice.feature.main.search.KakaoBookSearchSortType
import kotlinx.coroutines.flow.Flow

interface BookSearchRepository {

	suspend fun loadSearchData(
		query: String,
		sort: KakaoBookSearchSortType = KakaoBookSearchSortType.ACCURACY,
		page: Int = FIRST_PAGE,
		size: Int = DEFUALT_PAING_SIZE
	): Result<List<Book>>

	companion object {
		const val FIRST_PAGE = 1
		const val DEFUALT_PAING_SIZE = 20
	}
}
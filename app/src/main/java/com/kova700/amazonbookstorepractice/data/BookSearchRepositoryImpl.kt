package com.kova700.amazonbookstorepractice.data

import com.kova700.amazonbookstorepractice.data.api.BookSearchService
import com.kova700.amazonbookstorepractice.feature.main.search.KakaoBookSearchSortType
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookSearchRepositoryImpl @Inject constructor(
	private val bookSearchService: BookSearchService
) : BookSearchRepository {

	override suspend fun loadSearchData(
		query: String, sort: KakaoBookSearchSortType, page: Int, size: Int
	): Result<List<Book>> {
		return runCatching {
			bookSearchService.searchBooks(
				query = query, page = page,
				sort = sort, size = size
			).books
		}

	}

}
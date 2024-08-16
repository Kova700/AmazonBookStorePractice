package com.kova700.amazonbookstorepractice.core.data.booksearch.external.usecase

import com.kova700.amazonbookstorepractice.core.data.booksearch.external.model.Book
import com.kova700.amazonbookstorepractice.core.data.booksearch.external.model.KakaoBookSearchSortType
import com.kova700.amazonbookstorepractice.core.data.booksearch.external.repository.BookSearchRepository
import javax.inject.Inject

class GetSearchedBookUseCase @Inject constructor(
	private val repository: BookSearchRepository,
) {
	suspend operator fun invoke(
		query: String,
		sort: KakaoBookSearchSortType,
	): List<Book> {
		return repository.getSearchResult(
			query = query,
			sort = sort,
		)
	}
}
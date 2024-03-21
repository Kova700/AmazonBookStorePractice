package com.kova700.booksearch.usecase

import com.kova700.booksearch.model.Book
import com.kova700.booksearch.model.KakaoBookSearchSortType
import com.kova700.booksearch.repository.BookSearchRepository
import javax.inject.Inject

class GetSearchedBookUseCase @Inject constructor(
	private val repository: BookSearchRepository
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
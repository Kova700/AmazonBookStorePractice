package com.kova700.amazonbookstorepractice.domain.usecase

import com.kova700.amazonbookstorepractice.domain.model.Book
import com.kova700.amazonbookstorepractice.domain.repository.BookSearchRepository
import com.kova700.amazonbookstorepractice.ui.main.search.KakaoBookSearchSortType
import javax.inject.Inject

class GetSearchedBookUseCase @Inject constructor(
	private val repository: BookSearchRepository
) {
	suspend operator fun invoke(query: String, sort: KakaoBookSearchSortType): List<Book> {
		return repository.loadSearchData(
			query = query,
			sort = sort
		)
	}
}
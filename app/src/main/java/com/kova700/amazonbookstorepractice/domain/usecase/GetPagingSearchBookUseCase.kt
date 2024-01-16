package com.kova700.amazonbookstorepractice.domain.usecase

import com.kova700.amazonbookstorepractice.domain.model.Book
import com.kova700.amazonbookstorepractice.domain.repository.BookSearchRepository
import javax.inject.Inject

class GetPagingSearchBookUseCase @Inject constructor(
	private val repository: BookSearchRepository
) {
	suspend operator fun invoke(): List<Book> {
		return repository.loadMoreSearchData()
	}
}
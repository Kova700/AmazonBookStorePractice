package com.kova700.amazonbookstorepractice.core.data.booksearch.external.usecase

import com.kova700.amazonbookstorepractice.core.data.booksearch.external.model.Book
import com.kova700.amazonbookstorepractice.core.data.booksearch.external.repository.BookSearchRepository
import javax.inject.Inject

class GetCachedBookUseCase @Inject constructor(
	private val repository: BookSearchRepository,
) {
	operator fun invoke(index: Int): Book {
		return repository.getCachedBook(index)
	}
}
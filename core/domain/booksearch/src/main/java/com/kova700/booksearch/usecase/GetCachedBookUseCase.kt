package com.kova700.booksearch.usecase

import com.kova700.booksearch.model.Book
import com.kova700.booksearch.repository.BookSearchRepository
import javax.inject.Inject

class GetCachedBookUseCase @Inject constructor(
	private val repository: BookSearchRepository
) {
	operator fun invoke(index: Int): Book {
		return repository.getCachedBook(index)
	}
}
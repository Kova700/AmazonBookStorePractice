package com.kova700.amazonbookstorepractice.domain.usecase

import com.kova700.amazonbookstorepractice.domain.model.Book
import com.kova700.amazonbookstorepractice.domain.repository.BookSearchRepository
import javax.inject.Inject

class GetCachedBookUseCase @Inject constructor(
	private val repository: BookSearchRepository
) {
	operator fun invoke(index: Int): Book {
		return repository.getBook(index)
	}
}
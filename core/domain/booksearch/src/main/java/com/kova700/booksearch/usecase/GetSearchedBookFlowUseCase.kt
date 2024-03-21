package com.kova700.booksearch.usecase

import com.kova700.booksearch.model.Book
import com.kova700.booksearch.repository.BookSearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSearchedBookFlowUseCase @Inject constructor(
	private val repository: BookSearchRepository
) {
	operator fun invoke(): Flow<List<Book>> {
		return repository.getSearchResultFlow()
	}
}
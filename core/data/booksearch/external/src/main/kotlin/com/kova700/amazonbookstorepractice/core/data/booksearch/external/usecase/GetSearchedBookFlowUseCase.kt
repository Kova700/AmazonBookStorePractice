package com.kova700.amazonbookstorepractice.core.data.booksearch.external.usecase

import com.kova700.amazonbookstorepractice.core.data.booksearch.external.model.Book
import com.kova700.amazonbookstorepractice.core.data.booksearch.external.repository.BookSearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSearchedBookFlowUseCase @Inject constructor(
	private val repository: BookSearchRepository,
) {
	operator fun invoke(): Flow<List<Book>> {
		return repository.getSearchResultFlow()
	}
}
package com.kova700.amazonbookstorepractice.data.repository

import androidx.annotation.VisibleForTesting
import com.kova700.amazonbookstorepractice.data.api.BookSearchService
import com.kova700.amazonbookstorepractice.data.mapper.toDomain
import com.kova700.amazonbookstorepractice.domain.model.Book
import com.kova700.amazonbookstorepractice.domain.model.KakaoBookSearchSortType
import com.kova700.amazonbookstorepractice.domain.repository.BookSearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class BookSearchRepositoryImpl @Inject constructor(
	private val bookSearchService: BookSearchService
) : BookSearchRepository {

	@VisibleForTesting
	val books = MutableStateFlow<List<Book>>(emptyList())

	@VisibleForTesting
	var cachedSearchKeyword = ""

	@VisibleForTesting
	var cachedSortType = KakaoBookSearchSortType.ACCURACY

	@VisibleForTesting
	var cachedPage = FIRST_PAGE

	@VisibleForTesting
	var isEndPage = false

	override fun getSearchResultFlow(): Flow<List<Book>> {
		return books.asStateFlow()
	}

	override suspend fun getSearchResult(
		query: String, sort: KakaoBookSearchSortType
	): List<Book> {
		if (query == cachedSearchKeyword && sort == cachedSortType && isEndPage) return books.value

		if (query != cachedSearchKeyword || sort != cachedSortType) {
			clearCachedData(
				query = query,
				sort = sort
			)
		}

		val response = bookSearchService.searchBooks(
			query = query, page = cachedPage,
			sort = sort.toString().lowercase(),
			size = DEFAULT_PAGING_SIZE
		)

		val newData = response.books
			.filter { it.thumbnail.isNotBlank() }
			.map { it.copy(url = it.url.toMobileUrl()).toDomain() }

		books.update { books.value + newData }

		cachedSearchKeyword = query
		isEndPage = response.meta.isEnd
		cachedPage++
		return books.value
	}

	private fun clearCachedData(query: String, sort: KakaoBookSearchSortType) {
		books.update { emptyList() }
		cachedPage = FIRST_PAGE
		cachedSearchKeyword = query
		cachedSortType = sort
		isEndPage = false
	}

	override fun getCachedBook(index: Int): Book {
		return books.value[index]
	}

	companion object {
		const val FIRST_PAGE = 1
		const val DEFAULT_PAGING_SIZE = 20
	}

}

@VisibleForTesting
fun String.toMobileUrl(): String {
	return this.replace(
		oldValue = "https://",
		newValue = "https://m."
	)
}

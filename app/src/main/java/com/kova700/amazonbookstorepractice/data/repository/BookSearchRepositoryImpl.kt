package com.kova700.amazonbookstorepractice.data.repository

import com.kova700.amazonbookstorepractice.data.api.BookSearchService
import com.kova700.amazonbookstorepractice.data.mapper.toDomain
import com.kova700.amazonbookstorepractice.domain.model.Book
import com.kova700.amazonbookstorepractice.domain.model.KakaoBookSearchSortType
import com.kova700.amazonbookstorepractice.domain.repository.BookSearchRepository
import com.kova700.amazonbookstorepractice.domain.repository.BookSearchRepository.Companion.FIRST_PAGE
import javax.inject.Inject

class BookSearchRepositoryImpl @Inject constructor(
	private val bookSearchService: BookSearchService
) : BookSearchRepository {

	//TODO : Think : cachedBooks는 화면 이동 후, 현재 화면에서 사용되지 않는다면
	// 메모리에 계속 남아있는게 맞을까..? 사라지게 하는게 좋겠지..?

	private val cachedBooks = mutableListOf<Book>()
	private var cachedSearchKeyword = ""
	private var cachedSortType = KakaoBookSearchSortType.ACCURACY
	private var cachedPage = FIRST_PAGE
	private var isEndPage = false

	override suspend fun loadSearchData(
		query: String, sort: KakaoBookSearchSortType, page: Int, size: Int
	): List<Book> {

		if (query == cachedSearchKeyword &&
			sort == cachedSortType &&
			page < cachedPage
		) return cachedBooks

		val response = bookSearchService.searchBooks(
			query = query, page = page,
			sort = sort.toString().lowercase(), size = size
		)

		if (query != cachedSearchKeyword || sort != cachedSortType) {
			clearCachedData(
				query = query,
				sort = sort
			)
		}

		cachedBooks.addAll(response.books
			.filter { it.thumbnail.isNotBlank() }
			.map { it.copy(url = it.url.toMobileUrl()) }
			.toDomain())

		isEndPage = response.meta.isEnd
		cachedPage++

		return cachedBooks
	}

	override suspend fun loadMoreSearchData(): List<Book> {
		if (isEndPage) return cachedBooks

		return loadSearchData(
			query = cachedSearchKeyword,
			sort = cachedSortType,
			page = cachedPage
		)
	}

	//인덱스에 없는경우 예외 처리 해야함
	override fun getBook(index: Int): Book {
		return cachedBooks[index]
	}

	private fun String.toMobileUrl(): String {
		return this.replace(
			oldValue = "https://",
			newValue = "https://m."
		)
	}

	private fun clearCachedData(
		query: String,
		sort: KakaoBookSearchSortType
	) {
		cachedBooks.clear()
		cachedPage = FIRST_PAGE
		cachedSearchKeyword = query
		cachedSortType = sort
		isEndPage = false
	}

}
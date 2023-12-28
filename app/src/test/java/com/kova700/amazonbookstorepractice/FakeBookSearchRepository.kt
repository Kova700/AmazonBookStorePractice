package com.kova700.amazonbookstorepractice

import com.kova700.amazonbookstorepractice.data.mapper.toDomain
import com.kova700.amazonbookstorepractice.domain.model.Book
import com.kova700.amazonbookstorepractice.domain.repository.BookSearchRepository
import com.kova700.amazonbookstorepractice.ui.main.search.KakaoBookSearchSortType
import com.kova700.amazonbookstorepractice.util.getDummyList

class FakeBookSearchRepository : BookSearchRepository {

	private val cachedBooks = mutableListOf<Book>()

	override suspend fun loadSearchData(
		query: String,
		sort: KakaoBookSearchSortType,
		page: Int,
		size: Int
	): List<Book> {
		return cachedBooks.also {
			it.addAll(getDummyList().toDomain())
		}
	}

	override fun getBook(index: Int): Book {
		return cachedBooks[index]
	}

}
package com.kova700.amazonbookstorepractice.data.repository

import com.kova700.amazonbookstorepractice.data.api.BookSearchService
import com.kova700.amazonbookstorepractice.data.mapper.toDomain
import com.kova700.amazonbookstorepractice.domain.model.Book
import com.kova700.amazonbookstorepractice.domain.repository.BookSearchRepository
import com.kova700.amazonbookstorepractice.ui.main.search.KakaoBookSearchSortType
import javax.inject.Inject

class BookSearchRepositoryImpl @Inject constructor(
	private val bookSearchService: BookSearchService
) : BookSearchRepository {

	//TODO : Think : cachedBooks는 화면 이동 후, 현재 화면에서 사용되지 않는다면
	// 메모리에 계속 남아있는게 맞을까..? 사라지게 하는게 좋겠지..?

	//검색어가 바뀌면 cachedBooks 내용물도 수정되어야함
	//페이징해서 내용물을 가져오면, cachedBooks에 내용물이 추가되는 방식으로 수정되어야함

	private val cachedBooks = mutableListOf<Book>()

	override suspend fun loadSearchData(
		query: String, sort: KakaoBookSearchSortType, page: Int, size: Int
	): List<Book> {
		return bookSearchService.searchBooks(
			query = query, page = page,
			sort = sort, size = size
		).books.toDomain().also { books ->
			cachedBooks.addAll(books)
		}
	}

	//인덱스에 없는경우 예외 처리 해야함
	override fun getBook(index: Int): Book {
		return cachedBooks[index]
	}

}
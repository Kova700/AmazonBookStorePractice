package com.kova700.amazonbookstorepractice.data

import com.kova700.amazonbookstorepractice.data.api.BookSearchService
import com.kova700.amazonbookstorepractice.data.mapper.toDomain
import com.kova700.amazonbookstorepractice.data.repository.BookSearchRepositoryImpl
import com.kova700.amazonbookstorepractice.data.repository.BookSearchRepositoryImpl.Companion.DEFAULT_PAGING_SIZE
import com.kova700.amazonbookstorepractice.data.repository.toMobileUrl
import com.kova700.amazonbookstorepractice.data.response.BookResponse
import com.kova700.amazonbookstorepractice.data.response.Meta
import com.kova700.amazonbookstorepractice.data.response.NetworkBook
import com.kova700.amazonbookstorepractice.domain.model.KakaoBookSearchSortType
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.kotlin.whenever

class BookSearchRepositoryImplTest {

	private val bookSearchService = mock<BookSearchService>()
	private val bookSearchRepository = BookSearchRepositoryImpl(
		bookSearchService = bookSearchService
	)

	@Test
	fun `초기 값 검사`() {
		assertTrue(bookSearchRepository.books.value.isEmpty())
		assertTrue(bookSearchRepository.cachedSearchKeyword.isBlank())
		assertEquals(bookSearchRepository.cachedSortType, KakaoBookSearchSortType.ACCURACY)
		assertEquals(bookSearchRepository.cachedPage, BookSearchRepositoryImpl.FIRST_PAGE)
		assertFalse(bookSearchRepository.isEndPage)
	}

	@Test
	fun `검색 결과를 FLow에 방출`() = runTest {
		val enteredQuery = "코틀린"
		val enteredSort = KakaoBookSearchSortType.ACCURACY
		val enteredPage = bookSearchRepository.cachedPage
		val enteredSize = DEFAULT_PAGING_SIZE

		val mockResponse = BookResponse(
			books = listOf(
				NetworkBook.DEFAULT.copy(
					title = "테스트 Title1"
				),
				NetworkBook.DEFAULT.copy(
					title = "테스트 Title2"
				),
			), meta = Meta(
				isEnd = true,
				pageableCount = 1,
				totalCount = 1
			)
		)

		whenever(
			bookSearchService.searchBooks(
				query = enteredQuery,
				sort = enteredSort.toString().lowercase(),
				page = enteredPage,
				size = enteredSize
			)
		).thenReturn(mockResponse)

		bookSearchRepository.getSearchResult(
			query = enteredQuery,
			sort = enteredSort
		)
		val expectedResults = mockResponse.books
			.filter { it.thumbnail.isNotBlank() }
			.map { it.copy(url = it.url.toMobileUrl()).toDomain() }

		verify(bookSearchService).searchBooks(
			query = enteredQuery,
			sort = enteredSort.toString().lowercase(),
			page = enteredPage,
			size = enteredSize
		)

		assertEquals(
			bookSearchRepository.getSearchResultFlow().first(),
			bookSearchRepository.books.value
		)
		assertEquals(bookSearchRepository.books.value, expectedResults)
		assertEquals(bookSearchRepository.cachedSearchKeyword, enteredQuery)
		assertEquals(bookSearchRepository.cachedSortType, enteredSort)
		assertEquals(bookSearchRepository.cachedPage, enteredPage + 1)
		assertEquals(bookSearchRepository.isEndPage, true)
	}
}
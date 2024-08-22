package com.kova700.search.search

import androidx.lifecycle.SavedStateHandle
import com.kova700.amazonbookstorepractice.core.data.booksearch.external.model.Book
import com.kova700.amazonbookstorepractice.core.data.booksearch.external.model.KakaoBookSearchSortType
import com.kova700.amazonbookstorepractice.core.data.booksearch.external.repository.BookSearchRepository
import com.kova700.core.data.searchhistory.external.repository.SearchHistoryRepository
import com.kova700.search.MainCoroutineRule
import com.kova700.search.feature.mapper.toItemList
import com.kova700.search.feature.search.SearchViewModel
import com.kova700.search.feature.search.SearchViewModel.Companion.IS_TEST_FLAG
import com.kova700.search.feature.search.SearchViewState
import com.kova700.search.feature.search.UiState
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class SearchViewModelTest {

	@get:Rule
	val coroutineRule = MainCoroutineRule()

	private val searchRepository = mock<BookSearchRepository>()
	private val searchHistoryRepository = mock<SearchHistoryRepository>()

	private val savedStateHandle = SavedStateHandle().apply {
		set(IS_TEST_FLAG, true)
	}

	private val searchViewModel = SearchViewModel(
		savedStateHandle = savedStateHandle,
		searchRepository = searchRepository,
		searchHistoryRepository = searchHistoryRepository
	)

	private val mockSearchResponse = listOf(
		Book.Default.copy(
			isbn = "1",
			title = "테스트 Book 1"
		),
		Book.Default.copy(
			isbn = "2",
			title = "테스트 Book 2"
		),
		Book.Default.copy(
			isbn = "3",
			title = "테스트 Book 3"
		),
		Book.Default.copy(
			isbn = "4",
			title = "테스트 Book 4"
		)
	)

	@Test
	fun `초기 값 검사`() {
		assertEquals(SearchViewState.Default, searchViewModel.viewState.value)
	}

	@Test
	fun `검색어가 비어있다면 검색되지 않음`() = runTest {
		val emptySearchKeyword = ""

		searchViewModel.onChangeSearchKeyword(emptySearchKeyword)
		searchViewModel.searchKeyword()

		verify(searchRepository, never()).getSearchResult(
			query = emptySearchKeyword,
			sort = KakaoBookSearchSortType.ACCURACY
		)
	}

	@Test
	fun `검색어가 비어있지 않다면 검색 됨`() = runTest {
		val nonEmptySearchKeyword = "자바"

		whenever(
			searchRepository.getSearchResult(
				query = nonEmptySearchKeyword,
				sort = KakaoBookSearchSortType.ACCURACY
			)
		).thenReturn(mockSearchResponse)

		searchViewModel.onChangeSearchKeyword(nonEmptySearchKeyword)
		searchViewModel.searchKeyword()

		verify(searchRepository).getSearchResult(
			query = nonEmptySearchKeyword,
			sort = KakaoBookSearchSortType.ACCURACY
		)
	}

	@Test
	fun `검색된 데이터는 가지고 있던 isExpanded 여부와 combine되어야함`() = runTest {
		whenever(
			searchRepository.getSearchResultFlow()
		).thenReturn(flowOf(mockSearchResponse))

		searchViewModel._isExpanded.value = mapOf("1" to true, "4" to true)
		searchViewModel.observeSearchResult()

		verify(searchRepository).getSearchResultFlow() //이게 호출되는 이유가 뭐지?

		val expectedResults = mockSearchResponse.toItemList().map {
			if (it.isbn == "1" || it.isbn == "4") it.copy(isExpanded = true)
			else it
		}
		assertEquals(expectedResults, searchViewModel.viewState.value.books)
	}

	@Test
	fun `검색 시, Uistate가 UiState_LOADING으로 바뀐다`() = runTest {
		val nonEmptySearchKeyword = "자바"

		searchViewModel.onChangeSearchKeyword(nonEmptySearchKeyword)
		searchViewModel.searchKeyword(isLoadingTest = true)

		assertEquals(UiState.LOADING, searchViewModel.viewState.value.uiState)
	}

	@Test
	fun `검색 실패 시, Uistate가 UiState_ERROR으로 바뀐다`() = runTest {
		val nonEmptySearchKeyword = "자바"

		whenever(
			searchRepository.getSearchResult(
				query = nonEmptySearchKeyword,
				sort = KakaoBookSearchSortType.ACCURACY
			)
		).thenThrow(RuntimeException("Search API is Failed"))

		searchViewModel.onChangeSearchKeyword(nonEmptySearchKeyword)
		searchViewModel.searchKeyword()

		assertEquals(UiState.ERROR, searchViewModel.viewState.value.uiState)
	}

	@Test
	fun `검색 결과가 비었을 시, Uistate가 UiState_EMPTY으로 바뀐다`() = runTest {
		val nonEmptySearchKeyword = "자바"

		whenever(
			searchRepository.getSearchResult(
				query = nonEmptySearchKeyword,
				sort = KakaoBookSearchSortType.ACCURACY
			)
		).thenReturn(emptyList())

		searchViewModel.onChangeSearchKeyword(nonEmptySearchKeyword)
		searchViewModel.searchKeyword()

		assertEquals(UiState.EMPTY, searchViewModel.viewState.value.uiState)
	}

	@Test
	fun `검색 시, 검색기록에 검색어가 추가 되고 검색 됨`() = runTest {
		val nonEmptySearchKeyword = "자바"

		whenever(
			searchRepository.getSearchResult(
				query = nonEmptySearchKeyword,
				sort = KakaoBookSearchSortType.ACCURACY
			)
		).thenReturn(mockSearchResponse)

		searchViewModel.onChangeSearchKeyword(nonEmptySearchKeyword)
		searchViewModel.searchKeyword()

		verify(searchHistoryRepository).addHistory(searchKeyword = nonEmptySearchKeyword)
		verify(searchRepository).getSearchResult(
			query = nonEmptySearchKeyword,
			sort = KakaoBookSearchSortType.ACCURACY
		)
	}

	@Test
	fun `다음 페이지 로드`() = runTest {
		val nonEmptySearchKeyword = "자바"

		whenever(
			searchRepository.getSearchResult(
				query = nonEmptySearchKeyword,
				sort = KakaoBookSearchSortType.ACCURACY
			)
		).thenReturn(mockSearchResponse)

		searchViewModel.onChangeSearchKeyword(nonEmptySearchKeyword)
		searchViewModel.searchKeyword()
		searchViewModel.loadNextSearchResult()

		verify(searchRepository, times(2)).getSearchResult(
			query = nonEmptySearchKeyword,
			sort = KakaoBookSearchSortType.ACCURACY
		)
	}

	@Test
	fun `다음 페이지 로드 시, Uistate가 UiState_LOADING으로 바뀐다`() = runTest {
		val nonEmptySearchKeyword = "자바"

		whenever(
			searchRepository.getSearchResult(
				query = nonEmptySearchKeyword,
				sort = KakaoBookSearchSortType.ACCURACY
			)
		).thenReturn(mockSearchResponse)

		searchViewModel.onChangeSearchKeyword(nonEmptySearchKeyword)
		searchViewModel.searchKeyword()
		searchViewModel.loadNextSearchResult(isLoadingTest = true)

		assertEquals(UiState.LOADING, searchViewModel.viewState.value.uiState)
	}

}
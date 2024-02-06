package com.kova700.amazonbookstorepractice.search

import com.kova700.amazonbookstorepractice.MainCoroutineRule
import com.kova700.amazonbookstorepractice.domain.model.Book
import com.kova700.amazonbookstorepractice.domain.model.KakaoBookSearchSortType
import com.kova700.amazonbookstorepractice.domain.usecase.AddSearchHistoryUseCase
import com.kova700.amazonbookstorepractice.domain.usecase.ClearSearchHistoryUseCase
import com.kova700.amazonbookstorepractice.domain.usecase.GetPagingSearchBookUseCase
import com.kova700.amazonbookstorepractice.domain.usecase.GetSearchHistoryUseCase
import com.kova700.amazonbookstorepractice.domain.usecase.GetSearchedBookUseCase
import com.kova700.amazonbookstorepractice.domain.usecase.RemoveSearchHistoryUseCase
import com.kova700.amazonbookstorepractice.ui.main.mapper.toItemList
import com.kova700.amazonbookstorepractice.ui.main.search.SearchViewModel
import com.kova700.amazonbookstorepractice.ui.main.search.SearchViewState
import com.kova700.amazonbookstorepractice.ui.main.search.UiState
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class SearchViewModelTest {

	@get:Rule
	val coroutineRule = MainCoroutineRule()

	private val getSearchedBookUseCase = mock<GetSearchedBookUseCase>()
	private val getPagingSearchBookUseCase = mock<GetPagingSearchBookUseCase>()
	private val getSearchHistoryUseCase = mock<GetSearchHistoryUseCase>()
	private val addSearchHistoryUseCase = mock<AddSearchHistoryUseCase>()
	private val removeSearchHistoryUseCase = mock<RemoveSearchHistoryUseCase>()
	private val clearSearchHistoryUseCase = mock<ClearSearchHistoryUseCase>()

	private val searchViewModel = SearchViewModel(
		getSearchedBookUseCase = getSearchedBookUseCase,
		getPagingSearchBookUseCase = getPagingSearchBookUseCase,
		getSearchHistoryUseCase = getSearchHistoryUseCase,
		addSearchHistoryUseCase = addSearchHistoryUseCase,
		removeSearchHistoryUseCase = removeSearchHistoryUseCase,
		clearSearchHistoryUseCase = clearSearchHistoryUseCase,
	)

	private val mockSearchResponse = listOf(
		Book.Default.copy(
			title = "테스트 Book 1"
		),
		Book.Default.copy(
			title = "테스트 Book 2"
		)
	)

	private val mockHistoryResponse = listOf(
		"이것은", "검색기록", "테스트", "데이터"
	)

	@Test
	fun `초기 값 검사`() {
		assertEquals(SearchViewState.Default, searchViewModel.viewState.value)
	}

	@Test
	fun `검색어가 비어있다면 검색되지 않음`() = runTest {
		val emptySearchKeyword = ""

		searchViewModel.changeSearchKeyword(emptySearchKeyword)
		searchViewModel.searchKeyword()

		verify(getSearchedBookUseCase, never()).invoke(
			query = emptySearchKeyword,
			sort = KakaoBookSearchSortType.ACCURACY
		)
	}

	@Test
	fun `검색어가 비어있지 않다면 검색 됨`() = runTest {
		val nonEmptySearchKeyword = "자바"

		whenever(
			getSearchedBookUseCase.invoke(
				query = nonEmptySearchKeyword,
				sort = KakaoBookSearchSortType.ACCURACY
			)
		).thenReturn(mockSearchResponse)

		searchViewModel.changeSearchKeyword(nonEmptySearchKeyword)
		searchViewModel.searchKeyword()

		verify(getSearchedBookUseCase).invoke(
			query = nonEmptySearchKeyword,
			sort = KakaoBookSearchSortType.ACCURACY
		)

		val books = mockSearchResponse.toItemList()

		assertEquals(
			SearchViewState.Default.copy(
				searchKeyWord = nonEmptySearchKeyword,
				books = books,
				uiState = UiState.SUCCESS
			), searchViewModel.viewState.value
		)
	}

	@Test
	fun `검색 시, Uistate가 UiState_LOADING으로 바뀐다`() = runTest {
		val nonEmptySearchKeyword = "자바"

		searchViewModel.changeSearchKeyword(nonEmptySearchKeyword)
		searchViewModel.searchKeyword(isLoadingTest = true)

		assertEquals(UiState.LOADING, searchViewModel.viewState.value.uiState)
	}

	@Test
	fun `검색 실패 시 Uistate가 UiState_ERROR으로 바뀐다`() = runTest {
		val nonEmptySearchKeyword = "자바"

		whenever(
			getSearchedBookUseCase.invoke(
				query = nonEmptySearchKeyword,
				sort = KakaoBookSearchSortType.ACCURACY
			)
		).thenThrow(RuntimeException("Search API is Failed"))

		searchViewModel.changeSearchKeyword(nonEmptySearchKeyword)
		searchViewModel.searchKeyword()

		assertEquals(UiState.ERROR, searchViewModel.viewState.value.uiState)
	}

	@Test
	fun `검색기록 가져오기`() = runTest {
		whenever(getSearchHistoryUseCase.invoke())
			.thenReturn(mockHistoryResponse)

		searchViewModel.loadSearchHistory()
		verify(getSearchHistoryUseCase).invoke()

		assertEquals(mockHistoryResponse, searchViewModel.viewState.value.searchHistory.toList())
	}

	@Test
	fun `검색 시, 검색 기록 추가하기`() = runTest {
		val searchKeyword = "블라블라"
		searchViewModel.changeSearchKeyword(searchKeyword)
		searchViewModel.searchKeyword()

		verify(addSearchHistoryUseCase).invoke(searchKeyword)
	}

	@Test
	fun `검색기록 삭제하기`() = runTest {
		val removeIndex = 0
		searchViewModel.onHistoryRemoveClick(removeIndex)

		verify(removeSearchHistoryUseCase).invoke(removeIndex)
		verify(getSearchHistoryUseCase).invoke()
	}

	@Test
	fun `검색기록 초기화`() = runTest {
		searchViewModel.onHistoryClearClick()

		verify(clearSearchHistoryUseCase).invoke()
		verify(getSearchHistoryUseCase).invoke()
	}

	@Test
	fun `다음 페이지 로드`() = runTest {
		val pagingResponse =
			mockSearchResponse + mockSearchResponse.map { it.copy(title = "${it.title} 2") }

		whenever(
			getPagingSearchBookUseCase.invoke()
		).thenReturn(pagingResponse)

		searchViewModel.loadNextSearchData()
		verify(getPagingSearchBookUseCase).invoke()

		assertEquals(
			SearchViewState.Default.copy(
				books = pagingResponse.toItemList(),
				uiState = UiState.SUCCESS
			), searchViewModel.viewState.value
		)
	}

	@Test
	fun `다음 페이지 로드 시, Uistate가 UiState_LOADING으로 바뀐다`() = runTest {
		searchViewModel.loadNextSearchData(isLoadingTest = true)
		assertEquals(UiState.LOADING, searchViewModel.viewState.value.uiState)
	}

	@Test
	fun `다음 페이지 로드 실패 시, Uistate가 UiState_ERROR로 바뀐다`() = runTest {
		whenever(
			getPagingSearchBookUseCase.invoke()
		).thenThrow(RuntimeException("Search API is Failed"))

		searchViewModel.loadNextSearchData()
		assertEquals(UiState.ERROR, searchViewModel.viewState.value.uiState)
	}

}
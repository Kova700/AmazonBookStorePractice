package com.kova700.amazonbookstorepractice.search

import com.kova700.amazonbookstorepractice.MainCoroutineRule
import com.kova700.amazonbookstorepractice.domain.model.Book
import com.kova700.amazonbookstorepractice.domain.model.KakaoBookSearchSortType
import com.kova700.amazonbookstorepractice.domain.usecase.GetSearchedBookUseCase
import com.kova700.amazonbookstorepractice.ui.main.mapper.toItemList
import com.kova700.amazonbookstorepractice.ui.main.search.UiState
import com.kova700.amazonbookstorepractice.ui.main.search.SearchViewModel
import com.kova700.amazonbookstorepractice.ui.main.search.SearchViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withContext
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.doSuspendableAnswer
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class SearchViewModelTest {

	@get:Rule
	val coroutineRule = MainCoroutineRule()

	private val getSearchedBookUseCase = mock<GetSearchedBookUseCase>()

	//lateinit var, @InjectMocks는 왜 작동하지 않는걸까..?
	private val searchViewModel = SearchViewModel(getSearchedBookUseCase)

	private val mockSearchResponse = listOf(
		Book.Default.copy(
			title = "테스트 Book 1"
		),
		Book.Default.copy(
			title = "테스트 Book 2"
		)
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
	fun `검색 시, Uistate가 LoadState_LOADING으로 바뀐다`() = runTest {
		val nonEmptySearchKeyword = "자바"

		whenever(
			getSearchedBookUseCase.invoke(
				query = nonEmptySearchKeyword,
				sort = KakaoBookSearchSortType.ACCURACY
			)
		).doSuspendableAnswer {
			withContext(Dispatchers.IO) { delay(5000) }
			mockSearchResponse
		}

		searchViewModel.changeSearchKeyword(nonEmptySearchKeyword)
		searchViewModel.searchKeyword()

		assertEquals(UiState.LOADING, searchViewModel.viewState.value.uiState)
		//withContext(Dispatchers.IO)의 delay로 인해 루트 코루틴이 먼저 완료되어버림으로 상태는 아직도 Loading
	}

	@Test
	fun `검색 실패 시 Uistate가 LoadState_ERROR으로 바뀐다`() = runTest {
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

}
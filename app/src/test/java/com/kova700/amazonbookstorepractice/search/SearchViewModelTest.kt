package com.kova700.amazonbookstorepractice.search

import com.kova700.amazonbookstorepractice.domain.model.Book
import com.kova700.amazonbookstorepractice.domain.model.KakaoBookSearchSortType
import com.kova700.amazonbookstorepractice.domain.usecase.GetSearchedBookUseCase
import com.kova700.amazonbookstorepractice.ui.main.search.LoadState
import com.kova700.amazonbookstorepractice.ui.main.search.SearchViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class SearchViewModelTest {

	private val getSearchedBookUseCase = mock<GetSearchedBookUseCase>()

	//lateinit var, @InjectMocks는 왜 작동하지 않는걸까..?
	private val searchViewModel = SearchViewModel(getSearchedBookUseCase)

	@Before
	fun setUp() {
		Dispatchers.setMain(UnconfinedTestDispatcher())
	}

	@Test
	fun `초기 값 검사`() {
		assertEquals("", searchViewModel.viewState.value.searchKeyWord)
		assertEquals(LoadState.SUCCESS, searchViewModel.viewState.value.loadState)
		assertEquals(KakaoBookSearchSortType.ACCURACY, searchViewModel.viewState.value.sortType)
		assertTrue(searchViewModel.viewState.value.books.isEmpty())
	}

	@Test
	fun `검색어가 비어있다면 검색되지 않음`() = runTest {

		val emptySearchKeyword = ""

		val mockResponse = listOf(
			Book.Default.copy(
				title = "테스트 Book 1"
			),
			Book.Default.copy(
				title = "테스트 Book 2"
			)
		)

		whenever(
			getSearchedBookUseCase.invoke(
				query = emptySearchKeyword,
				sort = KakaoBookSearchSortType.ACCURACY
			)
		).thenReturn(mockResponse)

		searchViewModel.changeSearchKeyword(emptySearchKeyword)
		searchViewModel.searchKeyword()

		verify(getSearchedBookUseCase, never()).invoke(
			query = emptySearchKeyword,
			sort = KakaoBookSearchSortType.ACCURACY
		)

		assertEquals(LoadState.SUCCESS, searchViewModel.viewState.value.loadState)
	}

	@Test
	fun `검색어가 비어있지 않다면 검색 됨`() = runTest {

		val nonEmptySearchKeyword = "자바"

		val mockResponse = listOf(
			Book.Default.copy(
				title = "테스트 Book 1"
			),
			Book.Default.copy(
				title = "테스트 Book 2"
			)
		)

		whenever(
			getSearchedBookUseCase.invoke(
				query = nonEmptySearchKeyword,
				sort = KakaoBookSearchSortType.ACCURACY
			)
		).thenReturn(mockResponse)

		searchViewModel.changeSearchKeyword(nonEmptySearchKeyword)
		searchViewModel.searchKeyword()

		verify(getSearchedBookUseCase).invoke(
			query = nonEmptySearchKeyword,
			sort = KakaoBookSearchSortType.ACCURACY
		)

		println(searchViewModel.viewState.value)
		assertEquals(mockResponse.size, searchViewModel.viewState.value.books.size)
		assertEquals(LoadState.SUCCESS, searchViewModel.viewState.value.loadState)
	}

	@Test
	fun `검색어 검색 시, Uistate가 LoadState_LOADING으로 바뀐다`() = runTest {

//		val nonEmptySearchKeyword = "자바"
//
//		val mockResponse = listOf(
//			Book.Default.copy(
//				title = "테스트 Book 1"
//			),
//			Book.Default.copy(
//				title = "테스트 Book 2"
//			)
//		)
//
//		whenever(
//			getSearchedBookUseCase.invoke(
//				query = nonEmptySearchKeyword,
//				sort = KakaoBookSearchSortType.ACCURACY
//			)
//		).thenReturn(mockResponse)
//
//		searchViewModel.changeSearchKeyword(nonEmptySearchKeyword)
//		searchViewModel.searchKeyword()

		//TODO
	}

	@Test
	fun `검색 성공 시 Uistate가 LoadState_SUCCESS으로 바뀐다`() {
		//TODO
	}

	@Test
	fun `검색 실패 시 Uistate가 LoadState_ERROR으로 바뀐다`() {
		//TODO
	}

}
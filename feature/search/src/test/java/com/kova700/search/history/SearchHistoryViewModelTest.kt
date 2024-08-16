package com.kova700.search.history

import androidx.lifecycle.SavedStateHandle
import com.kova700.search.feature.search.component.searchhistory.SearchHistoryViewModel
import com.kova700.search.feature.search.component.searchhistory.SearchHistoryViewModel.Companion.IS_TEST_FLAG
import com.kova700.search.MainCoroutineRule
import com.kova700.core.data.searchhistory.external.usecase.ClearSearchHistoryUseCase
import com.kova700.core.data.searchhistory.external.usecase.GetSearchHistoryFlowUseCase
import com.kova700.core.data.searchhistory.external.usecase.MoveHistoryAtTheTopUseCase
import com.kova700.core.data.searchhistory.external.usecase.RemoveSearchHistoryUseCase
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.kotlin.whenever

class SearchHistoryViewModelTest {

	@get:Rule
	val coroutineRule = MainCoroutineRule()

	private val getSearchHistoryFlowUseCase = mock<GetSearchHistoryFlowUseCase>()
	private val removeSearchHistoryUseCase = mock<RemoveSearchHistoryUseCase>()
	private val clearSearchHistoryUseCase = mock<ClearSearchHistoryUseCase>()
	private val moveHistoryAtTheTopUseCase = mock<MoveHistoryAtTheTopUseCase>()

	private val savedStateHandle = SavedStateHandle().apply {
		set(IS_TEST_FLAG, true)
	}

	private val searchHistoryViewModel = SearchHistoryViewModel(
		savedStateHandle = savedStateHandle,
		getSearchHistoryFlowUseCase = getSearchHistoryFlowUseCase,
		removeSearchHistoryUseCase = removeSearchHistoryUseCase,
		clearSearchHistoryUseCase = clearSearchHistoryUseCase,
		moveHistoryAtTheTopUseCase = moveHistoryAtTheTopUseCase
	)

	private val mockHistoryResponse = listOf(
		"이것은", "검색기록", "테스트", "데이터"
	)

	@Test
	fun `초기 값 검사`() {
		assertEquals(persistentListOf<String>(), searchHistoryViewModel.viewState.value)
	}

	@Test
	fun `검색기록 가져오기`() = runTest {
		whenever(getSearchHistoryFlowUseCase.invoke())
			.thenReturn(flowOf(mockHistoryResponse))

		searchHistoryViewModel.observeSearchHistory()

		verify(getSearchHistoryFlowUseCase).invoke()
		assertEquals(mockHistoryResponse.toImmutableList(), searchHistoryViewModel.viewState.value)
	}

	@Test
	fun `검색기록 클릭 시, 히스토리 목록 상단으로 이동`() = runTest {
		val clickedIndex = 2

		searchHistoryViewModel.onHistoryClick(clickedIndex)

		verify(moveHistoryAtTheTopUseCase).invoke(clickedIndex)
	}

	@Test
	fun `검색기록 삭제하기`() = runTest {
		val removeIndex = 0

		searchHistoryViewModel.onHistoryRemoveClick(removeIndex)

		verify(removeSearchHistoryUseCase).invoke(removeIndex)
	}

	@Test
	fun `검색기록 초기화`() = runTest {
		searchHistoryViewModel.onHistoryClearClick()

		verify(clearSearchHistoryUseCase).invoke()
	}
}
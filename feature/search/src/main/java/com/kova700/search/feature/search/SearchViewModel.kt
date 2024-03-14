package com.kova700.amazonbookstorepractice.ui.main.search

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kova700.amazonbookstorepractice.ui.main.mapper.toItem
import com.kova700.booksearch.model.KakaoBookSearchSortType
import com.kova700.booksearch.usecase.GetSearchedBookFlowUseCase
import com.kova700.booksearch.usecase.GetSearchedBookUseCase
import com.kova700.searchhistory.usecase.AddSearchHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class SearchViewModel @Inject constructor(
	savedStateHandle: SavedStateHandle,
	private val getSearchedBookUseCase: GetSearchedBookUseCase,
	private val getSearchedBookFlowUseCase: GetSearchedBookFlowUseCase,
	private val addSearchHistoryUseCase: AddSearchHistoryUseCase,
) : ViewModel() {
	private val isTest = savedStateHandle.get<Boolean>(IS_TEST_FLAG) ?: false

	private val _viewState: MutableStateFlow<SearchViewState> =
		MutableStateFlow(SearchViewState.Default)
	val viewState = _viewState.asStateFlow()

	@VisibleForTesting
	val _isExpanded = MutableStateFlow<Map<String, Boolean>>(emptyMap()) //(isbn, boolean)

	init {
		if (isTest.not()) {
			observeSearchResult()
		}
	}

	@VisibleForTesting
	fun observeSearchResult() = viewModelScope.launch {
		getSearchedBookFlowUseCase().combine(_isExpanded) { books, isExpandedMap ->
			books.map { book ->
				if (isExpandedMap[book.isbn] == true) book.toItem(isExpanded = true)
				else book.toItem()
			}
		}.collect { newBooks -> updateState { copy(books = newBooks.toImmutableList()) } }
	}

	private fun getSearchResult(isLoadingTest: Boolean = false) = viewModelScope.launch {
		updateState { copy(uiState = UiState.LOADING) }
		if (isLoadingTest) return@launch

		runCatching {
			getSearchedBookUseCase(
				query = viewState.value.searchKeyWord,
				sort = viewState.value.sortType,
			)
		}.onSuccess { books ->
			updateState { copy(uiState = if (books.isEmpty()) UiState.EMPTY else UiState.SUCCESS) }
		}.onFailure { handleError(it) }
	}

	fun loadNextSearchResult(isLoadingTest: Boolean = false) {
		getSearchResult(isLoadingTest)
	}

	fun searchKeyword(isLoadingTest: Boolean = false) {
		if (viewState.value.searchKeyWord.isBlank()) return
		addHistory()
		getSearchResult(isLoadingTest)
	}

	fun changeSearchKeyword(keyword: String) {
		updateState { copy(searchKeyWord = keyword.trim()) }
	}

	private fun addHistory() = viewModelScope.launch {
		addSearchHistoryUseCase(viewState.value.searchKeyWord)
	}

	fun onKeywordClear() {
		changeSearchKeyword("")
	}

	fun showHistory() {
		updateState { copy(uiState = UiState.HISTORY) }
	}

	fun onHistoryClick(keyword: String) {
		updateState { copy(searchKeyWord = keyword) }
		searchKeyword()
	}

	fun onSortOptionChange(sortOption: KakaoBookSearchSortType) {
		updateState { copy(sortType = sortOption) }
	}

	fun onItemExpend(index: Int) {
		val newList = viewState.value.books.toMutableList()
		val columnCount = 2
		val rowIndex = index / columnCount

		//같은 행에 확장된 Item이 있다면 확장 취소 후 다른 Item 확장 표시
		for (i in 0 until columnCount) {
			if (i > newList.lastIndex) return
			newList[rowIndex * columnCount + i] =
				newList[rowIndex * columnCount + i].copy(isExpanded = false)
		}
		val targetItem = viewState.value.books[index]
		_isExpanded.update { _isExpanded.value + (targetItem.isbn to targetItem.isExpanded.not()) }

		newList[index] = targetItem
			.copy(isExpanded = targetItem.isExpanded.not())
		updateState { copy(books = newList.toPersistentList()) }
	}

	private inline fun updateState(block: SearchViewState.() -> SearchViewState) {
		_viewState.update {
			_viewState.value.block()
		}
	}

	private fun handleError(throwable: Throwable) {
		updateState { copy(uiState = UiState.ERROR) }
	}

	companion object {
		internal const val IS_TEST_FLAG = "IS_TEST_FLAG"
	}
}
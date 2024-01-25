package com.kova700.amazonbookstorepractice.ui.main.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kova700.amazonbookstorepractice.domain.model.KakaoBookSearchSortType
import com.kova700.amazonbookstorepractice.domain.usecase.AddSearchHistoryUseCase
import com.kova700.amazonbookstorepractice.domain.usecase.ClearSearchHistoryUseCase
import com.kova700.amazonbookstorepractice.domain.usecase.GetPagingSearchBookUseCase
import com.kova700.amazonbookstorepractice.domain.usecase.GetSearchHistoryUseCase
import com.kova700.amazonbookstorepractice.domain.usecase.GetSearchedBookUseCase
import com.kova700.amazonbookstorepractice.domain.usecase.RemoveSearchHistoryUseCase
import com.kova700.amazonbookstorepractice.ui.main.mapper.toItemList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
	private val getSearchedBookUseCase: GetSearchedBookUseCase,
	private val getPagingSearchBookUseCase: GetPagingSearchBookUseCase,
	private val getSearchHistoryUseCase: GetSearchHistoryUseCase,
	private val addSearchHistoryUseCase: AddSearchHistoryUseCase,
	private val removeSearchHistoryUseCase: RemoveSearchHistoryUseCase,
	private val clearSearchHistoryUseCase: ClearSearchHistoryUseCase,
) : ViewModel() {

	private val _viewState: MutableStateFlow<SearchViewState> =
		MutableStateFlow(SearchViewState.Default)
	val viewState = _viewState.asStateFlow()

	init {
		loadSearchHistory()
	}

	fun searchKeyword() {
		if (viewState.value.searchKeyWord.isBlank()) return

		updateState { copy(uiState = UiState.LOADING) }
		addHistory()
		loadSearchData()
	}

	fun changeSearchKeyword(keyword: String) {
		updateState { copy(searchKeyWord = keyword.trim()) }
	}

	private fun loadSearchData() {
		viewModelScope.launch {
			runCatching {
				getSearchedBookUseCase(
					query = viewState.value.searchKeyWord,
					sort = viewState.value.sortType,
				).toItemList()
			}.onSuccess { books ->
				updateState {
					copy(
						uiState = if (books.isNotEmpty()) UiState.SUCCESS else UiState.EMPTY,
						books = books,
					)
				}
			}.onFailure {
				updateState {
					copy(uiState = UiState.ERROR)
				}
			}
		}
	}

	fun loadNextSearchData() {
		updateState { copy(uiState = UiState.LOADING) }

		viewModelScope.launch {
			runCatching {
				getPagingSearchBookUseCase().toItemList()
			}.onSuccess { books ->
				updateState {
					copy(
						uiState = UiState.SUCCESS,
						books = books,
					)
				}
			}.onFailure {
				updateState { copy(uiState = UiState.ERROR) }
			}
		}
	}

	private fun loadSearchHistory() {
		viewModelScope.launch {
			runCatching {
				getSearchHistoryUseCase().toImmutableList()
			}.onSuccess { historyList ->
				updateState {
					copy(searchHistory = historyList)
				}
			}
		}
	}

	private fun addHistory() = viewModelScope.launch {
		addSearchHistoryUseCase(viewState.value.searchKeyWord)
	}

	fun onKeywordClear() {
		changeSearchKeyword("")
	}

	fun showHistory() {
		loadSearchHistory()
		updateState { copy(uiState = UiState.HISTORY) }
	}

	fun onHistoryClick(keyword: String) {
		updateState { copy(searchKeyWord = keyword) }
		searchKeyword()
	}

	fun onHistoryRemoveClick(index: Int) = viewModelScope.launch {
		removeSearchHistoryUseCase(index)
		loadSearchHistory()
	}

	fun onHistoryClearClick() = viewModelScope.launch {
		clearSearchHistoryUseCase()
		loadSearchHistory()
	}

	fun onSortOptionChange(sortOption: KakaoBookSearchSortType) {
		updateState { copy(sortType = sortOption) }
	}

	//수정 예정
	private inline fun updateState(block: SearchViewState.() -> SearchViewState) {
		_viewState.value = _viewState.value.block()
	}
}
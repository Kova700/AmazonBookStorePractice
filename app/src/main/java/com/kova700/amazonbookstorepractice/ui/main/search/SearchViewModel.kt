package com.kova700.amazonbookstorepractice.ui.main.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kova700.amazonbookstorepractice.domain.model.KakaoBookSearchSortType
import com.kova700.amazonbookstorepractice.domain.usecase.GetPagingSearchBookUseCase
import com.kova700.amazonbookstorepractice.domain.usecase.GetSearchedBookUseCase
import com.kova700.amazonbookstorepractice.ui.main.mapper.toItemList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
	private val getSearchedBookUseCase: GetSearchedBookUseCase,
	private val getPagingSearchBookUseCase: GetPagingSearchBookUseCase
) : ViewModel() {

	private val _viewState: MutableStateFlow<SearchViewState> =
		MutableStateFlow(SearchViewState.Default)
	val viewState = _viewState.asStateFlow()

	fun searchKeyword() {
		if (viewState.value.searchKeyWord.isBlank()) return

		updateState { copy(uiState = UiState.LOADING) }
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

	private inline fun updateState(block: SearchViewState.() -> SearchViewState) {
		_viewState.value = _viewState.value.block()
	}
}
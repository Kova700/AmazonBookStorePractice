package com.kova700.amazonbookstorepractice.ui.main.search

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kova700.amazonbookstorepractice.domain.usecase.GetSearchedBookUseCase
import com.kova700.amazonbookstorepractice.ui.main.mapper.toItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
	private val getSearchedBookUseCase: GetSearchedBookUseCase,
) : ViewModel() {

	private val _viewState: MutableStateFlow<SearchViewState> =
		MutableStateFlow(SearchViewState.Default)
	val viewState = _viewState.asStateFlow()

	fun searchKeyword(isLoadingTest: Boolean = false) {
		if (viewState.value.searchKeyWord.isBlank()) return

		updateState { copy(loadState = LoadState.LOADING) }
		if (isLoadingTest) return

		loadSearchData()
	}

	fun changeSearchKeyword(keyword: String) {
		updateState { copy(searchKeyWord = keyword) }
	}

	@VisibleForTesting
	fun loadSearchData() {
		viewModelScope.launch {
			runCatching {
				getSearchedBookUseCase(
					query = viewState.value.searchKeyWord,
					sort = viewState.value.sortType
				).map { it.toItem() }
			}.onSuccess { books ->
				updateState {
					copy(
						loadState = LoadState.SUCCESS,
						books = books.toImmutableList()
					)
				}
			}.onFailure {
				updateState {
					copy(loadState = LoadState.ERROR)
				}
			}
		}
	}

	private inline fun updateState(block: SearchViewState.() -> SearchViewState) {
		_viewState.value = _viewState.value.block()
	}
}
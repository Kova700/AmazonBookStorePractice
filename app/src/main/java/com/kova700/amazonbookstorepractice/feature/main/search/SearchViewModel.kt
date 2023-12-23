package com.kova700.amazonbookstorepractice.feature.main.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kova700.amazonbookstorepractice.data.BookSearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
	private val bookSearchRepository: BookSearchRepository
) : ViewModel() {

	private val _viewState: MutableStateFlow<SearchViewState> =
		MutableStateFlow(SearchViewState.Default)
	val viewState = _viewState.asStateFlow()

	fun searchKeyword() {
		updateState { copy(loadState = LoadState.LOADING) }
		loadSearchData()
	}

	fun changeSearchKeyword(keyword: String) {
		updateState { copy(searchKeyWord = keyword) }
	}

	private fun loadSearchData() {
		viewModelScope.launch {
			runCatching {
				bookSearchRepository.loadSearchData(
					query = viewState.value.searchKeyWord,
					sort = viewState.value.sortType
				)
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
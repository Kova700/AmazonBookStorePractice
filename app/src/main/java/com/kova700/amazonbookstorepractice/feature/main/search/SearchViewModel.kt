package com.kova700.amazonbookstorepractice.feature.main.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kova700.amazonbookstorepractice.data.BookSearchRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
	private val bookSearchRepository: BookSearchRepositoryImpl
) : ViewModel() {

	private val _viewState: MutableStateFlow<SearchViewState> = MutableStateFlow(SearchViewState())
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
			bookSearchRepository.loadSearchData(
				query = viewState.value.searchKeyWord,
				sort = viewState.value.sortType
			).onSuccess { books ->
				updateState {
					copy(
						loadState = LoadState.SUCCESS,
						books = books
					)
				}
			}
				.onFailure {
					updateState {
						Log.d("로그", "SearchViewModel: loadSearchData() - throwable :$it")
						copy(loadState = LoadState.ERROR)
					}
				}
		}

	}

	private inline fun updateState(block: SearchViewState.() -> SearchViewState) {
		val newState = _viewState.value.block()
		_viewState.value = newState
	}
}
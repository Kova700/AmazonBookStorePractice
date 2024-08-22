package com.kova700.feature.searchhistory

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kova700.core.data.searchhistory.external.repository.SearchHistoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchHistoryViewModel @Inject constructor(
	savedStateHandle: SavedStateHandle,
	private val searchHistoryRepository: SearchHistoryRepository,
) : ViewModel() {
	private val isTest = savedStateHandle.get<Boolean>(IS_TEST_FLAG) ?: false

	private val _viewState = MutableStateFlow<ImmutableList<String>>(persistentListOf())
	val viewState get() = _viewState.asStateFlow()

	init {
		if (isTest.not()) {
			observeSearchHistory()
		}
	}

	@VisibleForTesting
	fun observeSearchHistory() = viewModelScope.launch {
		searchHistoryRepository.getSearchHistoryFlow().collect { historyList ->
			_viewState.update { historyList.toImmutableList() }
		}
	}

	fun onHistoryClick(index: Int) = viewModelScope.launch {
		searchHistoryRepository.moveHistoryAtTheTop(index)
	}

	fun onHistoryRemoveClick(index: Int) = viewModelScope.launch {
		searchHistoryRepository.removeHistory(index)
	}

	fun onHistoryClearClick() = viewModelScope.launch {
		searchHistoryRepository.clearHistory()
	}

	companion object {
		internal const val IS_TEST_FLAG = "IS_TEST_FLAG"
	}
}
package com.kova700.amazonbookstorepractice.ui.main.search.component.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kova700.amazonbookstorepractice.domain.usecase.ClearSearchHistoryUseCase
import com.kova700.amazonbookstorepractice.domain.usecase.GetSearchHistoryFlowUseCase
import com.kova700.amazonbookstorepractice.domain.usecase.MoveHistoryAtTheTopUseCase
import com.kova700.amazonbookstorepractice.domain.usecase.RemoveSearchHistoryUseCase
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
	private val getSearchHistoryFlowUseCase: GetSearchHistoryFlowUseCase,
	private val removeSearchHistoryUseCase: RemoveSearchHistoryUseCase,
	private val clearSearchHistoryUseCase: ClearSearchHistoryUseCase,
	private val moveHistoryAtTheTopUseCase: MoveHistoryAtTheTopUseCase
) : ViewModel() {

	private val _viewState = MutableStateFlow<ImmutableList<String>>(persistentListOf())
	val viewState get() = _viewState.asStateFlow()

	init {
		observeSearchHistory()
	}

	private fun observeSearchHistory() = viewModelScope.launch {
		getSearchHistoryFlowUseCase().collect { historyList ->
			_viewState.update { historyList.toImmutableList() }
		}
	}

	fun onHistoryClick(index: Int) = viewModelScope.launch{
		moveHistoryAtTheTopUseCase(index)
	}

	fun onHistoryRemoveClick(index: Int) = viewModelScope.launch {
		removeSearchHistoryUseCase(index)
	}

	fun onHistoryClearClick() = viewModelScope.launch {
		clearSearchHistoryUseCase()
	}
}
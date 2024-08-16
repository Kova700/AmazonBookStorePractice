package com.kova700.search.feature.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.kova700.amazonbookstorepractice.core.data.booksearch.external.usecase.GetCachedBookUseCase
import com.kova700.search.feature.SELECTED_BOOK_INDEX
import com.kova700.search.feature.mapper.toItem
import com.kova700.search.feature.model.BookItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
internal class DetailViewModel @Inject constructor(
	private val savedStateHandle: SavedStateHandle,
	private val getCachedBookUseCase: GetCachedBookUseCase
) : ViewModel() {

	private val bookIndex: Int = savedStateHandle.get<Int>(SELECTED_BOOK_INDEX) ?: -1

	private val _viewState = MutableStateFlow<BookItem>(BookItem.Default)
	val viewState = _viewState.asStateFlow()

	init {
		loadBook(bookIndex)
	}

	private fun loadBook(index: Int) {
		_viewState.value = getCachedBookUseCase(index).toItem()
	}
}
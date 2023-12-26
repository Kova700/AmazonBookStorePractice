package com.kova700.amazonbookstorepractice.ui.main.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.kova700.amazonbookstorepractice.domain.model.Book
import com.kova700.amazonbookstorepractice.domain.usecase.GetCachedBookUseCase
import com.kova700.amazonbookstorepractice.ui.main.SELECTED_BOOK_INDEX
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
	private val savedStateHandle: SavedStateHandle,
	private val getCachedBookUseCase: GetCachedBookUseCase
) : ViewModel() {

	private val bookIndex: Int = savedStateHandle.get<Int>(SELECTED_BOOK_INDEX) ?: -1

	private val _viewState = MutableStateFlow<Book>(Book.Default)
	val viewState = _viewState.asStateFlow()

	init {
		loadBook(bookIndex)
	}

	private fun loadBook(index: Int) {
		_viewState.value = getCachedBookUseCase(index)
	}
}
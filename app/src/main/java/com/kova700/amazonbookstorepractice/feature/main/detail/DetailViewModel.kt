package com.kova700.amazonbookstorepractice.feature.main.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.kova700.amazonbookstorepractice.data.Book
import com.kova700.amazonbookstorepractice.data.BookSearchRepository
import com.kova700.amazonbookstorepractice.feature.main.SELECTED_BOOK_INDEX
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
	private val savedStateHandle: SavedStateHandle,
	private val bookSearchRepository: BookSearchRepository
) : ViewModel() {

	private val bookIndex: Int = savedStateHandle.get<Int>(SELECTED_BOOK_INDEX) ?: -1

	private val _viewState = MutableStateFlow<Book>(Book.Default)
	val viewState = _viewState.asStateFlow()

	init {
		loadBook(bookIndex)
	}

	private fun loadBook(index: Int) {
		_viewState.value = bookSearchRepository.getBook(index)
	}
}
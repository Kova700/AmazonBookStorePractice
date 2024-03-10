package com.kova700.amazonbookstorepractice.ui.main.search

import androidx.compose.runtime.Immutable
import com.kova700.amazonbookstorepractice.domain.model.KakaoBookSearchSortType
import com.kova700.amazonbookstorepractice.ui.main.model.BookItem
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Immutable
data class SearchViewState(
	val searchKeyWord: String,
	val uiState: UiState,
	val sortType: KakaoBookSearchSortType,
	val books: ImmutableList<BookItem>,
) {
	companion object {
		val Default = SearchViewState(
			searchKeyWord = "",
			uiState = UiState.HISTORY,
			sortType = KakaoBookSearchSortType.ACCURACY,
			books = persistentListOf(),
		)
	}
}

enum class UiState {
	SUCCESS,
	LOADING,
	ERROR,
	EMPTY,
	HISTORY
}
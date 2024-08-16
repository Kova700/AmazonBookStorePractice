package com.kova700.search.feature.search

import androidx.compose.runtime.Immutable
import com.kova700.amazonbookstorepractice.core.data.booksearch.external.model.KakaoBookSearchSortType
import com.kova700.search.feature.model.BookItem
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Immutable
internal data class SearchViewState(
	val searchKeyWord: String,
	val uiState: UiState,
	val sortType: KakaoBookSearchSortType,
	val books: ImmutableList<BookItem>,
) {
	companion object {
		val Default = SearchViewState(
			searchKeyWord = "",
			uiState = UiState.DEFAULT,
			sortType = KakaoBookSearchSortType.ACCURACY,
			books = persistentListOf(),
		)
	}
}

internal enum class UiState {
	DEFAULT,
	SUCCESS,
	LOADING,
	ERROR,
	EMPTY,
	HISTORY
}
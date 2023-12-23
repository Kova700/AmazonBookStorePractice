package com.kova700.amazonbookstorepractice.feature.main.search

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.kova700.amazonbookstorepractice.data.Book
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Immutable
data class SearchViewState(
	val searchKeyWord: String,
	val loadState: LoadState,
	val sortType: KakaoBookSearchSortType,
	val books: ImmutableList<Book>
) {
	companion object {
		val Default = SearchViewState(
			searchKeyWord = "",
			loadState = LoadState.SUCCESS,
			sortType = KakaoBookSearchSortType.ACCURACY,
			books = persistentListOf()
		)
	}
}

enum class LoadState {
	SUCCESS,
	LOADING,
	ERROR
}

enum class KakaoBookSearchSortType {
	ACCURACY, //  정확도 (기본값)
	RECENCY,  //  최신순
}
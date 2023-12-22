package com.kova700.amazonbookstorepractice.feature.main.search

import com.kova700.amazonbookstorepractice.data.Book

//@Stable 키워드에 대해서 좀 더 알아보자.
data class SearchViewState(
	val searchKeyWord: String,
	val loadState: LoadState,
	val sortType: KakaoBookSearchSortType,
	val books: List<Book>
) {
	companion object {
		val Default = SearchViewState(
			searchKeyWord = "",
			loadState = LoadState.SUCCESS,
			sortType = KakaoBookSearchSortType.ACCURACY,
			books = listOf()
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
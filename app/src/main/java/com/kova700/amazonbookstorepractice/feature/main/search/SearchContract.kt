package com.kova700.amazonbookstorepractice.feature.main.search

import com.kova700.amazonbookstorepractice.data.Book
import com.kova700.amazonbookstorepractice.data.getDummyList

//@Stable 키워드에 대해서 좀 더 알아보자.
data class SearchViewState(
	val searchKeyWord: String = "",
	val loadState: LoadState = LoadState.SUCCESS,
	val sortType: KakaoBookSearchSortType = KakaoBookSearchSortType.ACCURACY,
	val books : List<Book> = getDummyList()
)

enum class LoadState {
	SUCCESS,
	LOADING,
	ERROR
}

enum class KakaoBookSearchSortType {
	ACCURACY, //  정확도 (기본값)
	RECENCY,  //  최신순
}
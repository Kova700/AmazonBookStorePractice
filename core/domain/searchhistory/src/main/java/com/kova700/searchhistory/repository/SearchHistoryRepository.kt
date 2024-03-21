package com.kova700.searchhistory.repository

import kotlinx.coroutines.flow.Flow

interface SearchHistoryRepository {
	suspend fun addHistory(searchKeyword: String)
	suspend fun removeHistory(index: Int)
	suspend fun clearHistory()
	suspend fun moveHistoryAtTheTop(index: Int)
	fun getSearchHistoryFlow(): Flow<List<String>>
}
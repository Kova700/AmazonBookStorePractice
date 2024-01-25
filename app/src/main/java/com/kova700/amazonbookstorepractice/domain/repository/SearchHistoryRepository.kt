package com.kova700.amazonbookstorepractice.domain.repository

interface SearchHistoryRepository {
	suspend fun getHistory(): List<String>
	suspend fun addHistory(searchKeyword: String)
	suspend fun removeHistory(index: Int)
	suspend fun clearHistory()
}
package com.kova700.core.data.searchhistory.internal

import com.kova700.core.datastore.searchhistory.SearchHistoryDataStore
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class SearchHistoryRepositoryImplTest {

	private val dataStore = mock<SearchHistoryDataStore>()
	private val searchHistoryRepository = SearchHistoryRepositoryImpl(dataStore = dataStore)

	@Test
	fun getSearchHistoryFlow() = runTest {
		searchHistoryRepository.getSearchHistoryFlow()
		verify(dataStore).getSearchHistoryFlow()
	}

	@Test
	fun addHistory() = runTest {
		searchHistoryRepository.addHistory("테스트 검색어")
		verify(dataStore).addHistory("테스트 검색어")
	}

	@Test
	fun moveHistoryAtTheTop() = runTest {
		searchHistoryRepository.moveHistoryAtTheTop(0)
		verify(dataStore).moveHistoryAtTheTop(0)
	}

	@Test
	fun removeHistory() = runTest {
		searchHistoryRepository.removeHistory(0)
		verify(dataStore).removeHistory(0)
	}

	@Test
	fun clearHistory() = runTest {
		searchHistoryRepository.clearHistory()
		verify(dataStore).clearHistory()
	}
}
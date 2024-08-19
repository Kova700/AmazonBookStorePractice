package com.kova700.core.datastore.searchhistory

import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

class SearchHistoryDataStoreTest {

	@get:Rule(order = 1)
	val tempFolder: TemporaryFolder = TemporaryFolder.builder().assureDeletion().build()

	private val testDataStore =
		PreferenceDataStoreFactory.create(
			produceFile = { tempFolder.newFile("HISTORY_PREFERENCES_TEST.preferences_pb") },
		)

	private val searchHistoryDataStore = SearchHistoryDataStore(testDataStore)

	@Test
	fun 검색어를_입력하면_최근_기록이_앞_쪽으로_추가되어야함() = runTest {
		searchHistoryDataStore.addHistory("이것은")
		searchHistoryDataStore.addHistory("검색")
		searchHistoryDataStore.addHistory("기록")

		val expectedResults = listOf("기록", "검색", "이것은")
		val realData = searchHistoryDataStore.getSearchHistoryFlow().firstOrNull()
		assertEquals(realData, expectedResults)
		assertEquals(searchHistoryDataStore.cachedHistoryList, expectedResults)
	}

	@Test
	fun 검색기록을_누르면_해당_기록_상단으로_이동() = runTest {
		searchHistoryDataStore.addHistory("이것은")
		searchHistoryDataStore.addHistory("검색")
		searchHistoryDataStore.addHistory("기록")
		searchHistoryDataStore.moveHistoryAtTheTop(2)

		val expectedResults = listOf("이것은", "기록", "검색")
		val realData = searchHistoryDataStore.getSearchHistoryFlow().firstOrNull()
		assertEquals(realData, expectedResults)
		assertEquals(searchHistoryDataStore.cachedHistoryList, expectedResults)
	}

	@Test
	fun 검색어_삭제() = runTest {
		searchHistoryDataStore.addHistory("이것은")
		searchHistoryDataStore.addHistory("검색")
		searchHistoryDataStore.addHistory("기록")
		searchHistoryDataStore.removeHistory(index = 0)

		val expectedResults = listOf("검색", "이것은")
		val realData = searchHistoryDataStore.getSearchHistoryFlow().firstOrNull()
		assertEquals(realData, expectedResults)
		assertEquals(searchHistoryDataStore.cachedHistoryList, expectedResults)
	}

	@Test
	fun 검색기록_초기화() = runTest {
		searchHistoryDataStore.addHistory("기록")
		searchHistoryDataStore.clearHistory()

		val realData = searchHistoryDataStore.getSearchHistoryFlow().firstOrNull()
		assertTrue(realData?.isEmpty() ?: true)
		assertTrue(searchHistoryDataStore.cachedHistoryList.isEmpty())
	}

}
package com.kova700.searchhistory

import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import com.kova700.core.data.searchhistory.internal.SearchHistoryRepositoryImpl
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

class SearchHistoryRepositoryImplTest {

	@get:Rule(order = 1)
	val tempFolder: TemporaryFolder = TemporaryFolder.builder().assureDeletion().build()

	private val testDataStore =
		PreferenceDataStoreFactory.create(
			produceFile = { tempFolder.newFile("HISTORY_PREFERENCES_TEST.preferences_pb") },
		)
	private val searchHistoryRepository = SearchHistoryRepositoryImpl(dataStore = testDataStore)

	@Test
	fun 초기값_검사() {
		assertTrue(searchHistoryRepository.cachedHistoryList.isEmpty())
	}

	@Test
	fun 검색어를_입력하면_최근_기록이_앞_쪽으로_추가되어야함() = runTest {
		searchHistoryRepository.addHistory("이것은")
		searchHistoryRepository.addHistory("검색")
		searchHistoryRepository.addHistory("기록")

		val expectedResults = listOf("기록", "검색", "이것은")
		val realData = searchHistoryRepository.getSearchHistoryFlow().firstOrNull()
		assertEquals(realData, expectedResults)
		assertEquals(searchHistoryRepository.cachedHistoryList, expectedResults)
	}

	@Test
	fun 검색기록을_누르면_해당_기록_상단으로_이동() = runTest {
		searchHistoryRepository.addHistory("이것은")
		searchHistoryRepository.addHistory("검색")
		searchHistoryRepository.addHistory("기록")
		searchHistoryRepository.moveHistoryAtTheTop(2)

		val expectedResults = listOf("이것은", "기록", "검색")
		val realData = searchHistoryRepository.getSearchHistoryFlow().firstOrNull()
		assertEquals(realData, expectedResults)
		assertEquals(searchHistoryRepository.cachedHistoryList, expectedResults)
	}

	@Test
	fun 검색어_삭제() = runTest {
		searchHistoryRepository.addHistory("이것은")
		searchHistoryRepository.addHistory("검색")
		searchHistoryRepository.addHistory("기록")
		searchHistoryRepository.removeHistory(index = 0)

		val expectedResults = listOf("검색", "이것은")
		val realData = searchHistoryRepository.getSearchHistoryFlow().firstOrNull()
		assertEquals(realData, expectedResults)
		assertEquals(searchHistoryRepository.cachedHistoryList, expectedResults)
	}

	@Test
	fun 검색기록_초기화() = runTest {
		searchHistoryRepository.addHistory("기록")
		searchHistoryRepository.clearHistory()

		val realData = searchHistoryRepository.getSearchHistoryFlow().firstOrNull()
		assertTrue(realData?.isEmpty() ?: true)
		assertTrue(searchHistoryRepository.cachedHistoryList.isEmpty())
	}

}
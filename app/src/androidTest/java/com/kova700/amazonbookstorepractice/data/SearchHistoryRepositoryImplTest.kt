package com.kova700.amazonbookstorepractice.data

import android.content.Context
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.test.core.app.ApplicationProvider
import com.kova700.amazonbookstorepractice.data.repository.SearchHistoryRepositoryImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

@OptIn(ExperimentalCoroutinesApi::class)
class SearchHistoryRepositoryImplTest {

	private val testContext: Context = ApplicationProvider.getApplicationContext()

//	@get:Rule(order = 0)
//	val coroutineRule = MainCoroutineRule()

	@get:Rule(order = 1)
	val tempFolder: TemporaryFolder = TemporaryFolder.builder().assureDeletion().build()
	//Unit Text 작성 시 아래와 같은 윈도우 운영체제 이슈로 인해서 계측 테스트로 이동
	//(tempFolder 방식과 Context 방식 둘 다 가능)
	//Unable to rename ~~ 경로 ~~ This likely means that there are multiple instances of DataStore for this file.
	// Ensure that you are only creating a single instance of datastore for this file.

	private val testCoroutineDispatcher = UnconfinedTestDispatcher()
//	private val testCoroutineScope = TestScope(testCoroutineDispatcher)

	private val testDataStore =
		PreferenceDataStoreFactory.create(
//			scope = testCoroutineScope,
//			produceFile = { testContext.preferencesDataStoreFile("HISTORY_PREFERENCES_TEST") },
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
		assertEquals(searchHistoryRepository.getHistory(), expectedResults)
		assertEquals(searchHistoryRepository.cachedHistoryList, expectedResults)
	}

	@Test
	fun 검색어_삭제() = runTest {
		searchHistoryRepository.addHistory("이것은")
		searchHistoryRepository.addHistory("검색")
		searchHistoryRepository.addHistory("기록")
		searchHistoryRepository.removeHistory(index = 0)

		val expectedResults = listOf("검색", "이것은")
		assertEquals(searchHistoryRepository.getHistory(), expectedResults)
		assertEquals(searchHistoryRepository.cachedHistoryList, expectedResults)
	}

	@Test
	fun 검색기록_초기화() = runTest {
		searchHistoryRepository.addHistory("기록")

		searchHistoryRepository.clearHistory()

		assertTrue(searchHistoryRepository.cachedHistoryList.isEmpty())
		assertTrue(searchHistoryRepository.getHistory().isEmpty())
	}

}
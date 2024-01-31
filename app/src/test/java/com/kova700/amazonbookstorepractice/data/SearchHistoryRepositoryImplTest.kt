package com.kova700.amazonbookstorepractice.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.kova700.amazonbookstorepractice.MainCoroutineRule
import com.kova700.amazonbookstorepractice.data.repository.SearchHistoryRepositoryImpl
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class SearchHistoryRepositoryImplTest {

	@get:Rule
	val coroutineRule = MainCoroutineRule()

	private val dataStore = mock<DataStore<Preferences>>()

	private val searchHistoryRepository = SearchHistoryRepositoryImpl(
		dataStore = dataStore
	)

	@Before
	fun init() {
		whenever(dataStore.data).thenReturn(flowOf(emptyPreferences()))

	}

	@Test
	fun `초기값 검사`() {
		assertTrue(searchHistoryRepository.cachedHistoryList.isEmpty())
	}

	@Test
	fun `검색기록 가져오기`() {

	}

	@Test
	fun `검색기록 초기화`() = runTest {
//		searchHistoryRepository.clearHistory()
//		whenever(dataStore.edit { mutablePreferences ->
//			mutablePreferences.remove(stringPreferencesKey("HISTORY_KEY"))
//		}).thenReturn(emptyPreferences())
//
//
//		verify(dataStore).edit { mutablePreferences ->
//			mutablePreferences.remove(stringPreferencesKey("HISTORY_KEY"))
//		}
//		assertTrue(searchHistoryRepository.cachedHistoryList.isEmpty())
	}

}
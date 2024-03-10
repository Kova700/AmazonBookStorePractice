package com.kova700.amazonbookstorepractice.data.repository

import androidx.annotation.VisibleForTesting
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.kova700.amazonbookstorepractice.domain.repository.SearchHistoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class SearchHistoryRepositoryImpl @Inject constructor(
	private val dataStore: DataStore<Preferences>
) : SearchHistoryRepository {
	private val historyKey = stringPreferencesKey("HISTORY_KEY")

	@VisibleForTesting
	var cachedHistoryList = mutableListOf<String>()

	override fun getSearchHistoryFlow(): Flow<List<String>> {
		return dataStore.data
			.catch { emit(emptyPreferences()) }
			.map {
				it[historyKey]?.let { historyString ->
					Json.decodeFromString<List<String>>(historyString)
				} ?: emptyList()
			}.onEach { cachedHistoryList = it.toMutableList() }
	}

	override suspend fun addHistory(searchKeyword: String) {
		if (cachedHistoryList.firstOrNull() == searchKeyword) return

		if (cachedHistoryList.contains(searchKeyword).not()) {
			cachedHistoryList.add(0, searchKeyword)
		}
		updateDataStore(cachedHistoryList)
	}

	override suspend fun moveHistoryAtTheTop(index: Int) {
		val keyword = cachedHistoryList[index]
		cachedHistoryList.removeAt(index)
		cachedHistoryList.add(0, keyword)
		updateDataStore(cachedHistoryList)
	}

	override suspend fun removeHistory(index: Int) {
		cachedHistoryList.removeAt(index)
		updateDataStore(cachedHistoryList)
	}

	override suspend fun clearHistory() {
		dataStore.edit { mutablePreferences ->
			mutablePreferences.remove(historyKey)
		}
		cachedHistoryList.clear()
	}

	private suspend fun updateDataStore(newList: List<String>) {
		val newHistoryString = Json.encodeToString(newList)
		dataStore.edit { mutablePreferences ->
			mutablePreferences[historyKey] = newHistoryString
		}
	}

}
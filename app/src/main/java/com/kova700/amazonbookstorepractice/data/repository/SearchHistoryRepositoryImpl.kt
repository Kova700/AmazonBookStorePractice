package com.kova700.amazonbookstorepractice.data.repository

import androidx.annotation.VisibleForTesting
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.kova700.amazonbookstorepractice.domain.repository.SearchHistoryRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class SearchHistoryRepositoryImpl @Inject constructor(
	private val dataStore: DataStore<Preferences>
) : SearchHistoryRepository {
	private val historyKey = stringPreferencesKey("HISTORY_KEY")

	@VisibleForTesting
	var cachedHistoryList = mutableListOf<String>()

	override suspend fun getHistory(): List<String> {
		if (cachedHistoryList.isNotEmpty()) return cachedHistoryList

		val historyString = dataStore.data
			.catch { emit(emptyPreferences()) }
			.firstOrNull()?.get(historyKey)

		if (historyString.isNullOrBlank()) return emptyList()

		return Json.decodeFromString<List<String>>(historyString)
			.also { cachedHistoryList.addAll(it) }
	}

	override suspend fun addHistory(searchKeyword: String) {
		if (cachedHistoryList.firstOrNull() == searchKeyword) return

		cachedHistoryList = mutableListOf(searchKeyword)
			.apply { addAll(cachedHistoryList) }
			.toMutableSet().toMutableList()
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
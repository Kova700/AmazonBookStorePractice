package com.kova700.core.datastore.searchhistory

import androidx.annotation.VisibleForTesting
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.kova700.core.datastore.datastore.clearData
import com.kova700.core.datastore.datastore.getDataFlow
import com.kova700.core.datastore.datastore.setData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class SearchHistoryDataStore @Inject constructor(
	private val dataStore: DataStore<Preferences>,
) {
	private val historyKey = stringPreferencesKey("HISTORY_KEY")

	@VisibleForTesting
	var cachedHistoryList = mutableListOf<String>()

	fun getSearchHistoryFlow(): Flow<List<String>> {
		return dataStore.getDataFlow(historyKey)
			.map { historyString ->
				historyString?.let {
					Json.decodeFromString<List<String>>(historyString)
				} ?: emptyList()
			}.onEach { cachedHistoryList = it.toMutableList() }
	}

	suspend fun addHistory(searchKeyword: String) {
		if (cachedHistoryList.firstOrNull() == searchKeyword) return

		if (cachedHistoryList.contains(searchKeyword).not()) {
			cachedHistoryList.add(0, searchKeyword)
		}
		updateDataStore(cachedHistoryList)
	}

	suspend fun moveHistoryAtTheTop(index: Int) {
		val keyword = cachedHistoryList[index]
		cachedHistoryList.removeAt(index)
		cachedHistoryList.add(0, keyword)
		updateDataStore(cachedHistoryList)
	}

	suspend fun removeHistory(index: Int) {
		cachedHistoryList.removeAt(index)
		updateDataStore(cachedHistoryList)
	}

	suspend fun clearHistory() {
		dataStore.clearData(historyKey)
		cachedHistoryList.clear()
	}

	private suspend fun updateDataStore(newList: List<String>) {
		val newHistoryString = Json.encodeToString(newList)
		dataStore.setData(historyKey, newHistoryString)
	}

}
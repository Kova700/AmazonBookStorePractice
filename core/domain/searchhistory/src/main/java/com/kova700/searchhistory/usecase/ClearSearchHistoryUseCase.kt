package com.kova700.searchhistory.usecase

import com.kova700.searchhistory.repository.SearchHistoryRepository
import javax.inject.Inject

class ClearSearchHistoryUseCase @Inject constructor(
	private val repository: SearchHistoryRepository
) {
	suspend operator fun invoke() {
		repository.clearHistory()
	}
}
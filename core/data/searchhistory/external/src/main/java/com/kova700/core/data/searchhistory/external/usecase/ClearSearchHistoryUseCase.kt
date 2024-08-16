package com.kova700.core.data.searchhistory.external.usecase

import com.kova700.core.data.searchhistory.external.repository.SearchHistoryRepository
import javax.inject.Inject

class ClearSearchHistoryUseCase @Inject constructor(
	private val repository: SearchHistoryRepository
) {
	suspend operator fun invoke() {
		repository.clearHistory()
	}
}
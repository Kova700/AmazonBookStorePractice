package com.kova700.core.data.searchhistory.external.usecase

import com.kova700.core.data.searchhistory.external.repository.SearchHistoryRepository
import javax.inject.Inject

class RemoveSearchHistoryUseCase @Inject constructor(
	private val repository: SearchHistoryRepository
) {
	suspend operator fun invoke(index: Int) {
		repository.removeHistory(index)
	}
}
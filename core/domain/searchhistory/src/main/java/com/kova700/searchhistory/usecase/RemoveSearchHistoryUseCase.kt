package com.kova700.searchhistory.usecase

import com.kova700.searchhistory.repository.SearchHistoryRepository
import javax.inject.Inject

class RemoveSearchHistoryUseCase @Inject constructor(
	private val repository: SearchHistoryRepository
) {
	suspend operator fun invoke(index: Int) {
		repository.removeHistory(index)
	}
}
package com.kova700.searchhistory.usecase

import com.kova700.searchhistory.repository.SearchHistoryRepository
import javax.inject.Inject

class AddSearchHistoryUseCase @Inject constructor(
	private val repository: SearchHistoryRepository
) {
	suspend operator fun invoke(searchKeyword: String) {
		repository.addHistory(searchKeyword)
	}
}
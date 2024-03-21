package com.kova700.searchhistory.usecase

import com.kova700.searchhistory.repository.SearchHistoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSearchHistoryFlowUseCase @Inject constructor(
	private val repository: SearchHistoryRepository
) {
	operator fun invoke(): Flow<List<String>> {
		return repository.getSearchHistoryFlow()
	}
}
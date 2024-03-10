package com.kova700.amazonbookstorepractice.domain.usecase

import com.kova700.amazonbookstorepractice.domain.repository.SearchHistoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSearchHistoryFlowUseCase @Inject constructor(
	private val repository: SearchHistoryRepository
) {
	operator fun invoke(): Flow<List<String>> {
		return repository.getSearchHistoryFlow()
	}
}
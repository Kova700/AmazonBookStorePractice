package com.kova700.amazonbookstorepractice.domain.usecase

import com.kova700.amazonbookstorepractice.domain.repository.SearchHistoryRepository
import javax.inject.Inject

class MoveHistoryAtTheTopUseCase @Inject constructor(
	private val repository: SearchHistoryRepository
) {
	suspend operator fun invoke(index: Int) {
		return repository.moveHistoryAtTheTop(index)
	}
}
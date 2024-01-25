package com.kova700.amazonbookstorepractice.data.di

import com.kova700.amazonbookstorepractice.data.repository.BookSearchRepositoryImpl
import com.kova700.amazonbookstorepractice.data.repository.SearchHistoryRepositoryImpl
import com.kova700.amazonbookstorepractice.domain.repository.BookSearchRepository
import com.kova700.amazonbookstorepractice.domain.repository.SearchHistoryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

	@Binds
	@Singleton
//	@Reusable
	fun bindBookSearchRepository(repository: BookSearchRepositoryImpl): BookSearchRepository

	@Binds
	@Singleton
	fun bindSearchHistoryRepository(repository: SearchHistoryRepositoryImpl): SearchHistoryRepository
}
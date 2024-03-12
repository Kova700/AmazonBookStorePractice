package com.kova700.searchhistory.di

import com.kova700.searchhistory.SearchHistoryRepositoryImpl
import com.kova700.searchhistory.repository.SearchHistoryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface SearchHistoryRepositoryModule {

	@Binds
	@Singleton
	fun bindSearchHistoryRepository(repository: SearchHistoryRepositoryImpl): SearchHistoryRepository
}
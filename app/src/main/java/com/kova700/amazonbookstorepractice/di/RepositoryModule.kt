package com.kova700.amazonbookstorepractice.di

import com.kova700.amazonbookstorepractice.data.BookSearchRepository
import com.kova700.amazonbookstorepractice.data.BookSearchRepositoryImpl
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
	fun bindBookSearchRepository(repository: BookSearchRepositoryImpl): BookSearchRepository
}
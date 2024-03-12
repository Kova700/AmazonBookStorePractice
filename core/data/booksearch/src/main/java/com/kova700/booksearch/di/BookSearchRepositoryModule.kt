package com.kova700.booksearch.di

import com.kova700.booksearch.BookSearchRepositoryImpl
import com.kova700.booksearch.repository.BookSearchRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface BookSearchRepositoryModule {
	@Binds
	@Singleton
//	@Reusable
	fun bindBookSearchRepository(repository: BookSearchRepositoryImpl): BookSearchRepository

}
package com.kova700.amazonbookstorepractice.core.data.booksearch.internal.di

import com.kova700.amazonbookstorepractice.core.data.booksearch.external.repository.BookSearchRepository
import com.kova700.amazonbookstorepractice.core.data.booksearch.internal.repository.BookSearchRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface BookSearchRepositoryModule {

	@Binds
	@Singleton
//	@Reusable
	fun bindBookSearchRepository(repository: BookSearchRepositoryImpl): BookSearchRepository

}
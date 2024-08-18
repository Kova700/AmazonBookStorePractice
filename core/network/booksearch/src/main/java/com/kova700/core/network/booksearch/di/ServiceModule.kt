package com.kova700.core.network.booksearch.di

import com.kova700.core.network.booksearch.BookSearchNetworkService
import com.kova700.core.network.booksearch.KtorBookSearchService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface ServiceModule {

	@Binds
	@Singleton
	fun provideBookSearchService(bookSearchService: KtorBookSearchService): BookSearchNetworkService
}
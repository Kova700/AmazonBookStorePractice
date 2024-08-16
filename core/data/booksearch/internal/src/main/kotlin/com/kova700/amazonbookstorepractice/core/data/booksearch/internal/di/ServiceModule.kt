package com.kova700.amazonbookstorepractice.core.data.booksearch.internal.di

import com.kova700.amazonbookstorepractice.core.data.booksearch.internal.api.BookSearchService
import com.kova700.amazonbookstorepractice.core.data.booksearch.internal.api.KtorBookSearchService
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
	fun provideBookSearchService(bookSearchService: KtorBookSearchService): BookSearchService
}
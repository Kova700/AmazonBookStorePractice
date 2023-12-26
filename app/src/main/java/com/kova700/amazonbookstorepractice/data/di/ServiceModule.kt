package com.kova700.amazonbookstorepractice.data.di

import com.kova700.amazonbookstorepractice.data.api.BookSearchService
import com.kova700.amazonbookstorepractice.data.api.KtorBookSearchService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface ServiceModule {

	@Binds
	@Singleton
	fun provideBookSearchService(bookSearchService: KtorBookSearchService): BookSearchService
}
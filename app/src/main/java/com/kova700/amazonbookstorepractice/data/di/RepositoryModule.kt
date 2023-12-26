package com.kova700.amazonbookstorepractice.data.di

import com.kova700.amazonbookstorepractice.data.repositoryiml.BookSearchRepositoryImpl
import com.kova700.amazonbookstorepractice.domain.repository.BookSearchRepository
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
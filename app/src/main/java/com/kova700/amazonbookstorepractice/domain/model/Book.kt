package com.kova700.amazonbookstorepractice.domain.model

import androidx.compose.runtime.Immutable
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Immutable
data class Book(
	val title: String,
	val thumbnail: String,
	val authors: ImmutableList<String>,
	val price: Int,
	val contents: String,
	val datetime: String,
	val isbn: String,
	val publisher: String,
	val status: String,
	val translators: ImmutableList<String>,
	val url: String,
	val salePrice: Int
) {
	companion object {
		val Default = Book(
			title = "",
			thumbnail = "",
			authors = persistentListOf(),
			price = 0,
			contents = "",
			datetime = "",
			isbn = "",
			publisher = "",
			status = "",
			translators = persistentListOf(),
			url = "",
			salePrice = 0
		)
	}
}
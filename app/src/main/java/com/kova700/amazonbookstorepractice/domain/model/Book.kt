package com.kova700.amazonbookstorepractice.domain.model

data class Book(
	val title: String,
	val thumbnail: String,
	val authors: List<String>,
	val price: Int,
	val contents: String,
	val datetime: String,
	val isbn: String,
	val publisher: String,
	val status: String,
	val translators: List<String>,
	val url: String,
	val salePrice: Int
) {
	companion object {
		val Default = Book(
			title = "",
			thumbnail = "",
			authors = listOf(),
			price = 0,
			contents = "",
			datetime = "",
			isbn = "",
			publisher = "",
			status = "",
			translators = listOf(),
			url = "",
			salePrice = 0
		)
	}
}
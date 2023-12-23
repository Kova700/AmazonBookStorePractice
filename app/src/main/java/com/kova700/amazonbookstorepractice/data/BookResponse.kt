package com.kova700.amazonbookstorepractice.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BookResponse(
	@SerialName("documents") val books: List<Book>,
	val meta: Meta
)

@Serializable
data class Meta(
	@SerialName("is_end") val isEnd: Boolean,
	@SerialName("pageable_count") val pageableCount: Int,
	@SerialName("total_count") val totalCount: Int
)

@Serializable
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
	@SerialName("sale_price") val salePrice: Int
){
	companion object{
		val Default = Book(
			title = "",
			thumbnail = "",
			authors = emptyList(),
			price = 0,
			contents = "",
			datetime = "",
			isbn = "",
			publisher = "",
			status = "",
			translators = emptyList(),
			url = "",
			salePrice = 0
		)
	}
}

//TODO : search.daum => m.search.daum으로 수정해주는 로직 필요
package com.kova700.amazonbookstorepractice.core.data.booksearch.internal.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class BookResponse(
	@SerialName("documents") val books: List<NetworkBook> = emptyList(),
	@SerialName("meta") val meta: Meta
)

@Serializable
internal data class Meta(
	@SerialName("is_end") val isEnd: Boolean,
	@SerialName("pageable_count") val pageableCount: Int,
	@SerialName("total_count") val totalCount: Int
)

@Serializable
internal data class NetworkBook(
	@SerialName("title") val title: String,
	@SerialName("thumbnail") val thumbnail: String,
	@SerialName("authors") val authors: List<String>,
	@SerialName("price") val price: Int,
	@SerialName("contents") val contents: String,
	@SerialName("datetime") val datetime: String,
	@SerialName("isbn") val isbn: String,
	@SerialName("publisher") val publisher: String,
	@SerialName("status") val status: String,
	@SerialName("translators") val translators: List<String>,
	@SerialName("url") val url: String,
	@SerialName("sale_price") val salePrice: Int
) {
	companion object {
		val DEFAULT = NetworkBook(
			title = "",
			thumbnail = "",
			authors = emptyList(),
			price = 1,
			contents = "",
			datetime = "",
			isbn = "",
			publisher = "",
			status = "",
			translators = emptyList(),
			url = "",
			salePrice = 1
		)
	}
}
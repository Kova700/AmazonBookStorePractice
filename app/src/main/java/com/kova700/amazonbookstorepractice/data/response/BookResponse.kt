package com.kova700.amazonbookstorepractice.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BookResponse(
	@SerialName("documents") val books: List<NetworkBook> = emptyList(),
	@SerialName("meta") val meta: Meta
)

@Serializable
data class Meta(
	@SerialName("is_end") val isEnd: Boolean,
	@SerialName("pageable_count") val pageableCount: Int,
	@SerialName("total_count") val totalCount: Int
)

@Serializable
data class NetworkBook(
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
)
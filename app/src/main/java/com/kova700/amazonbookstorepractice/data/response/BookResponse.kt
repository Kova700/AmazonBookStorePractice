package com.kova700.amazonbookstorepractice.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BookResponse(
	@SerialName("documents") val books: List<NetworkBook>,
	val meta: Meta
)

@Serializable
data class Meta(
	@SerialName("is_end") val isEnd: Boolean,
	@SerialName("pageable_count") val pageableCount: Int,
	@SerialName("total_count") val totalCount: Int
)

@Serializable
data class NetworkBook(
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
)

//TODO : search.daum => m.search.daum으로 수정해주는 로직 필요
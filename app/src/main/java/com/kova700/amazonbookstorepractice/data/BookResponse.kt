package com.kova700.amazonbookstorepractice.data

import androidx.compose.runtime.Immutable
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.collections.immutable.toPersistentList
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
)

fun Book.toEntity() = BookEntity(
	title = title,
	thumbnail = thumbnail,
	authors = authors.toImmutableList(),
	price = price,
	contents = contents,
	datetime = datetime,
	isbn = isbn,
	publisher = publisher,
	status = status,
	translators = translators.toImmutableList(),
	url = url,
	salePrice = salePrice
)

fun List<Book>.toEntity() = this.map { it.toEntity() }

@Immutable
data class BookEntity(
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
		val Default = BookEntity(
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

//TODO : search.daum => m.search.daum으로 수정해주는 로직 필요
package com.kova700.amazonbookstorepractice.data.mapper

import com.kova700.amazonbookstorepractice.data.response.NetworkBook
import com.kova700.amazonbookstorepractice.domain.model.Book
import kotlinx.collections.immutable.toImmutableList

fun NetworkBook.toDomain() = Book(
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

fun List<NetworkBook>.toDomain() = this.map { it.toDomain() }
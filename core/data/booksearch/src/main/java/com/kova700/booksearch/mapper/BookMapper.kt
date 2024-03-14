package com.kova700.amazonbookstorepractice.data.mapper

import com.kova700.booksearch.model.Book
import com.kova700.booksearch.model.NetworkBook
import kotlinx.collections.immutable.toImmutableList

internal fun NetworkBook.toDomain() = Book(
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
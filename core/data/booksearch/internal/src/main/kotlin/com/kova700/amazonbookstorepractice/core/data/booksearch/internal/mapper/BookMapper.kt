package com.kova700.amazonbookstorepractice.core.data.booksearch.internal.mapper

import com.kova700.amazonbookstorepractice.core.data.booksearch.external.model.Book
import com.kova700.core.network.booksearch.model.NetworkBook

internal fun NetworkBook.toDomain() = Book(
	title = title,
	thumbnail = thumbnail,
	authors = authors,
	price = price,
	contents = contents,
	datetime = datetime,
	isbn = isbn,
	publisher = publisher,
	status = status,
	translators = translators,
	url = url,
	salePrice = salePrice
)
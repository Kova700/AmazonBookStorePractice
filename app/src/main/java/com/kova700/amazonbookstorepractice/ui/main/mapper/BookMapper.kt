package com.kova700.amazonbookstorepractice.ui.main.mapper

import com.kova700.amazonbookstorepractice.domain.model.Book
import com.kova700.amazonbookstorepractice.ui.main.model.BookItem
import kotlinx.collections.immutable.toImmutableList

fun Book.toItem() =
	BookItem(
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
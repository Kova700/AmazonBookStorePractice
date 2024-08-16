package com.kova700.search.feature.mapper

import com.kova700.amazonbookstorepractice.core.data.booksearch.external.model.Book
import com.kova700.search.feature.model.BookItem
import kotlinx.collections.immutable.toImmutableList

internal fun Book.toItem(isExpanded: Boolean = false) =
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
		salePrice = salePrice,
		isExpanded = isExpanded
	)

internal fun List<Book>.toItemList() = this.map { it.toItem() }
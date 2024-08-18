plugins {
	id("convention.android.library")
	id("convention.android.hilt")
}

android {
	namespace = "com.kova700.amazonbookstorepractice.core.data.booksearch.internal"
}

dependencies {
	implementation(project(":core:data:booksearch:external"))
	implementation(project(":core:network:booksearch"))
}
plugins {
	id("convention.android.feature")
}

android {
	namespace = "com.kova700.amazonbookstorepractice.feature.search"
}

dependencies {
	implementation(project(":core:data:searchhistory:external"))
	implementation(project(":core:data:booksearch:external"))
}
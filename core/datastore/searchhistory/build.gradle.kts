plugins {
	id("convention.android.library")
	id("convention.android.hilt")
	alias(libs.plugins.kotlin.serialization)
}

android {
	namespace = "com.kova700.amazonbookstorepractice.core.datastore.searchhistory"
}

dependencies {
	implementation(project(":core:datastore:datastore"))
	implementation(libs.androidx.datastore.preferences)
	implementation(libs.kotlinx.serialization.json)
}
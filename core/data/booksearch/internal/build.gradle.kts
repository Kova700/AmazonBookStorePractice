plugins {
	id("convention.android.library")
	id("convention.android.hilt")
	alias(libs.plugins.kotlin.serialization)
}

android {
	namespace = "com.kova700.amazonbookstorepractice.core.data.booksearch.internal"
}

dependencies {
	implementation(project(":core:data:booksearch:external"))
	implementation(libs.bundles.ktor)
	implementation(libs.kotlinx.collections.immutable)
}
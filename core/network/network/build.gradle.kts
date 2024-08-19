plugins {
	id("convention.android.library")
	id("convention.android.hilt")
}

android {
	namespace = "com.kova700.amazonbookstorepractice.core.network.network"
}

dependencies {
	implementation(libs.bundles.ktor)
}
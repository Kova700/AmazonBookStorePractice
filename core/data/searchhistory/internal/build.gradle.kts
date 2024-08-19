plugins {
	id("convention.android.library")
	id("convention.android.hilt")
}

android {
	namespace = "com.kova700.amazonbookstorepractice.core.data.searchhistory.internal"
}

dependencies {
	implementation(project(":core:data:searchhistory:external"))
	implementation(project(":core:datastore:searchhistory"))
	testImplementation(libs.bundles.unit.test)
	androidTestImplementation(libs.bundles.android.test)
}
plugins {
	id("convention.android.application")
	id("convention.android.compose")
	id("convention.android.hilt")
}

android {
	namespace = "com.kova700.amazonbookstorepractice"
	defaultConfig {
		versionCode = libs.versions.versionCode.get().toInt()
		val majorVersion = libs.versions.major.get()
		val minorVersion = libs.versions.minor.get()
		val hotfixVersion = libs.versions.hotfix.get()
		versionName = "$majorVersion.$minorVersion.$hotfixVersion"
	}
}

dependencies {
	implementation(project(":feature:main"))
	implementation(project(":core:data:booksearch:internal"))
	implementation(project(":core:data:searchhistory:internal"))
	implementation(libs.androidx.core)
	implementation(libs.bundles.androidx.lifecycle)
}
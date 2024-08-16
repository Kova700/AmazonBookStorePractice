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
	implementation(project(":core:network"))
	implementation(project(":core:data:booksearch:external"))
	implementation(project(":core:data:booksearch:internal"))
	implementation(project(":core:data:searchhistory:external"))
	implementation(project(":core:data:searchhistory:internal"))
	implementation(project(":core:design-system"))
	implementation(project(":core:datastore:datastore"))
	implementation(project(":core:datastore:searchhistory"))
	implementation(project(":feature:search"))
	implementation(libs.androidx.core)
	implementation(libs.bundles.androidx.lifecycle)
}
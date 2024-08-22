plugins {
	id("convention.android.feature")
}

android {
	namespace = "com.kova700.amazonbookstorepractice.feature.main"
}

dependencies {
	implementation(project(":feature:search"))
}
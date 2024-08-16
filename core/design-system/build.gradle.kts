@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
	id("convention.android.library")
	id("convention.android.hilt")
	id("convention.android.compose")
}
android {
	namespace = "com.kova700.amazonbookstorepractice.core.design_system"
}
dependencies {}
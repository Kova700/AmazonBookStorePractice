
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
	id("convention.android.application")
	id("convention.android.compose")
	id("convention.android.hilt")

//	alias(libs.plugins.android.application)
//	alias(libs.plugins.kotlin.android)
//	id("com.android.application")
//	kotlin("android")
//	alias(libs.plugins.ksp)
//	alias(libs.plugins.android.hilt)
//	alias(libs.plugins.kotlin.serialization)
}

android {
	namespace = "com.kova700.amazonbookstorepractice"
}

dependencies {
	implementation(project(":core:network"))
	implementation(project(":core:domain:booksearch"))
	implementation(project(":core:domain:searchhistory"))
	implementation(project(":core:data:booksearch"))
	implementation(project(":core:data:searchhistory"))
	implementation(project(":core:design-system"))
	implementation(project(":core:datastore"))
	implementation(project(":feature:search"))

	implementation(libs.androidx.core)
	implementation(libs.androidx.lifecycle.runtime.ktx)
	implementation(libs.androidx.lifecycle.runtime.compose)

	implementation(libs.compose.activity)
	implementation(libs.compose.ui)
	implementation(libs.compose.ui.graphics)
	implementation(libs.compose.ui.tooling.preview)
	implementation(libs.compose.material3)
	implementation(libs.compose.navigation)

	implementation(libs.test.androidx.core.ktx)
	implementation(libs.test.androidx.junit.ktx)

	testImplementation(libs.junit)
	testImplementation(libs.test.mockito.core)
	testImplementation(libs.test.mockito.inline)
	testImplementation(libs.test.mockito.kotlin)
	testImplementation(libs.test.coroutines)

	androidTestImplementation(libs.test.androidx.junit)
	androidTestImplementation(libs.test.androidx.espresso.core)
	androidTestImplementation(libs.test.compose.ui.junit4)

	debugImplementation(libs.test.compose.ui.manifest)
	debugImplementation(libs.compose.ui.tooling)

	implementation(libs.hilt.android)
	ksp(libs.hilt.android.compiler)
	implementation(libs.hilt.navigation.compose)

	implementation(libs.kotlinx.serialization.json)

	implementation(libs.kotlinx.collections.immutable)

	implementation(libs.coil.compose)

	implementation(libs.ktor.client.core)
	implementation(libs.ktor.client.android)
	implementation(libs.ktor.client.logging)
	implementation(libs.ktor.client.content.negotiation)
	implementation(libs.ktor.serialization.kotlinx.json)

	implementation(libs.androidx.datastore.preferences)
}
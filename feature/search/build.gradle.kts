import internal.android

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
	alias(libs.plugins.android.library)
	alias(libs.plugins.kotlin.android)
	alias(libs.plugins.android.hilt)
	alias(libs.plugins.ksp)
}

android {
	namespace = "com.kova700.search"
	compileSdk = libs.versions.compileSdk.get().toInt()

	defaultConfig {
		minSdk = libs.versions.minSdk.get().toInt()

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
		consumerProguardFiles("consumer-rules.pro")
	}

	buildTypes {
		release {
			isMinifyEnabled = false
			proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
		}
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_17
		targetCompatibility = JavaVersion.VERSION_17
	}
	kotlinOptions {
		jvmTarget = "17"
	}
	buildFeatures {
		compose = true
	}
	composeOptions {
		kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
	}
}

dependencies {

	implementation(project(":core:design-system"))
	implementation(project(":core:domain:booksearch"))
	implementation(project(":core:domain:searchhistory"))

	implementation(libs.androidx.core)
	implementation(libs.androidx.lifecycle.runtime.ktx)
	implementation(libs.androidx.lifecycle.runtime.compose)
	implementation(libs.androidx.lifecycle.viewmodel)

	implementation(libs.appcompat)
	implementation(libs.material)
	testImplementation(libs.junit)
	androidTestImplementation(libs.test.androidx.junit)
	androidTestImplementation(libs.test.androidx.espresso.core)

	implementation(libs.compose.activity)
	implementation(libs.compose.ui)
	implementation(libs.compose.ui.graphics)
	implementation(libs.compose.ui.tooling.preview)
	implementation(libs.compose.material3)
	implementation(libs.compose.navigation)
	debugImplementation(libs.compose.ui.tooling)

	testImplementation(libs.junit)
	implementation(libs.test.androidx.core.ktx)
	implementation(libs.test.androidx.junit.ktx)
	testImplementation(libs.test.mockito.core)
	testImplementation(libs.test.mockito.inline)
	testImplementation(libs.test.mockito.kotlin)
	testImplementation(libs.test.coroutines)
	androidTestImplementation(libs.test.androidx.junit)
	androidTestImplementation(libs.test.androidx.espresso.core)
	androidTestImplementation(libs.test.compose.ui.junit4)
	debugImplementation(libs.test.compose.ui.manifest)

	implementation(libs.coil.compose)

	implementation(libs.hilt.android)
	ksp(libs.hilt.android.compiler)
	implementation(libs.hilt.navigation.compose)

	implementation(libs.kotlinx.collections.immutable)
}
import internal.android

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
	alias(libs.plugins.android.library)
	alias(libs.plugins.kotlin.android)
	alias(libs.plugins.android.hilt)
	alias(libs.plugins.ksp)
	alias(libs.plugins.kotlin.serialization)
}

android {
	namespace = "com.kova700.searchhistory"
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
}

dependencies {
	implementation(project(":core:domain:searchhistory"))
	implementation(project(":core:datastore"))

	implementation(libs.androidx.core)
	implementation(libs.appcompat)
	implementation(libs.material)

	implementation(libs.hilt.android)
	ksp(libs.hilt.android.compiler)

	implementation(libs.ktor.serialization.kotlinx.json)

	implementation(libs.androidx.datastore.preferences)

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
}
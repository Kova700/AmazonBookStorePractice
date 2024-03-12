@Suppress("DSL_SCOPE_VIOLATION")
plugins {
	alias(libs.plugins.android.application)
	alias(libs.plugins.kotlin.android)
	alias(libs.plugins.ksp)
	alias(libs.plugins.android.hilt)
	alias(libs.plugins.kotlin.serialization)
}

android {
	namespace = "com.kova700.amazonbookstorepractice"
	compileSdk = libs.versions.compileSdk.get().toInt()

	defaultConfig {
		applicationId = "com.kova700.amazonbookstorepractice"
		minSdk = libs.versions.minSdk.get().toInt()
		targetSdk = libs.versions.targetSdk.get().toInt()
		versionCode = libs.versions.versionCode.get().toInt()
		versionName =
			"${libs.versions.major.get()}.${libs.versions.minor.get()}.${libs.versions.hotfix.get()}"

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
		vectorDrawables {
			useSupportLibrary = true
		}
	}

	buildTypes {
		release {
			isMinifyEnabled = false
			proguardFiles(
				getDefaultProguardFile("proguard-android-optimize.txt"),
				"proguard-rules.pro"
			)
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
	packaging {
		resources {
			excludes += "/META-INF/{AL2.0,LGPL2.1}"
		}
	}
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
import gradle.configure.libs

plugins {
	id("convention.android.library")
	id("convention.android.compose")
	id("convention.android.hilt")
}

android {
	defaultConfig {
		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
	}
}

dependencies {
	implementation(project(":core:design-system"))
	implementation(libs.findLibrary("androidx-core").get())
	implementation(libs.findBundle("androidx-lifecycle").get())
	implementation(libs.findLibrary("appcompat").get())
	implementation(libs.findLibrary("material").get())
	testImplementation(libs.findBundle("unit-test").get())
	androidTestImplementation(libs.findBundle("android-test").get())
	debugImplementation(libs.findBundle("debug-test").get())
	implementation(libs.findLibrary("coil-compose").get())
	implementation(libs.findLibrary("kotlinx-collections-immutable").get())
}

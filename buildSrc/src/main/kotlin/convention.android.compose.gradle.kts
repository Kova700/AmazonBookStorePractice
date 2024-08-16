import gradle.configure.android
import gradle.configure.libs

plugins {
	kotlin("plugin.compose")
}

android {
	buildFeatures.compose = true
}

composeCompiler {
	enableStrongSkippingMode = true
}

dependencies {
	//추후 수정
	"implementation"(libs.findBundle("compose").get())
	"implementation"(libs.findLibrary("hilt-navigation-compose").get())

	"androidTestImplementation"(libs.findLibrary("test-androidx-junit").get())
	"androidTestImplementation"(libs.findLibrary("test-compose-ui-junit4").get())
	"androidTestImplementation"(libs.findLibrary("test-androidx-espresso-core").get())

	"debugImplementation"(libs.findLibrary("test-compose-ui-manifest").get())
	"debugImplementation"(libs.findLibrary("compose-ui-tooling").get())
}
plugins {
	id("convention.android.application")
}

val versionCatalog = project.extensions.getByType<VersionCatalogsExtension>()
val libs = versionCatalog.named("libs")

android {
	buildFeatures.compose = true
	composeOptions.kotlinCompilerExtensionVersion =
		libs.findVersion("compose-compiler").get().toString()
}

dependencies {
	"implementation"(libs.findLibrary("compose-material3").get())
	"implementation"(libs.findLibrary("compose-ui").get())
	"implementation"(libs.findLibrary("compose-activity").get())
	"implementation"(libs.findLibrary("compose-ui-graphics").get())
	"implementation"(libs.findLibrary("compose-ui-tooling").get())
	"implementation"(libs.findLibrary("compose-ui-tooling-preview").get())
	"implementation"(libs.findLibrary("compose-navigation").get())
	"implementation"(libs.findLibrary("hilt-navigation-compose").get())

	"androidTestImplementation"(libs.findLibrary("test-androidx-junit").get())
	"androidTestImplementation"(libs.findLibrary("test-compose-ui-junit4").get())
	"androidTestImplementation"(libs.findLibrary("test-androidx-espresso-core").get())

	"debugImplementation"(libs.findLibrary("test-compose-ui-manifest").get())
}

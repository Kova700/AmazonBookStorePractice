plugins {
	id("com.google.devtools.ksp")
	id("dagger.hilt.android.plugin")
}

val versionCatalog = project.extensions.getByType<VersionCatalogsExtension>()
val libs = versionCatalog.named("libs")

dependencies {
	"ksp"(libs.findLibrary("hilt-android-compiler").get())
	"implementation"(libs.findLibrary("hilt-android").get())
}

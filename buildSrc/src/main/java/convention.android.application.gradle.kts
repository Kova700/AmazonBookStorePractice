plugins {
	id("com.android.application")
	kotlin("android")
}

val versionCatalog = project.extensions.getByType<VersionCatalogsExtension>()
val libs = versionCatalog.named("libs")

android {
	compileSdk = libs.findVersion("compileSdk").get().requiredVersion.toInt()

	defaultConfig {
		minSdk = libs.findVersion("minSdk").get().requiredVersion.toInt()
		targetSdk = libs.findVersion("targetSdk").get().requiredVersion.toInt()
		versionCode = libs.findVersion("versionCode").get().requiredVersion.toInt()
		vectorDrawables.useSupportLibrary = true
		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

		val majorVersion = libs.findVersion("major").get().requiredVersion
		val minorVersion = libs.findVersion("minor").get().requiredVersion
		val hotfixVersion = libs.findVersion("hotfix").get().requiredVersion
		versionName = "$majorVersion.$minorVersion.$hotfixVersion"
	}

	kotlinOptions {
		jvmTarget = "17"
	}

	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_17
		targetCompatibility = JavaVersion.VERSION_17
	}

	buildTypes {
		release {
			isMinifyEnabled = true
			isShrinkResources = true
			proguardFiles(
				getDefaultProguardFile("proguard-android-optimize.txt"),
				"proguard-rules.pro"
			)
		}
	}
}
import java.io.FileInputStream
import java.util.Properties

var properties = Properties().apply {
	load(FileInputStream("./core/network/local.properties"))
}

plugins {
	id("convention.android.library")
	id("convention.android.hilt")
}

android {
	namespace = "com.kova700.amazonbookstorepractice.core.network"
	defaultConfig.buildConfigField(
		type = "String",
		name = "KAKAO_REST_API_KEY",
		value = properties.getProperty("KAKAO_REST_API_KEY")
	)
	buildFeatures.buildConfig = true
}

dependencies {
	implementation(libs.bundles.ktor)
}
import java.io.FileInputStream
import java.util.Properties

var properties = Properties().apply {
	load(FileInputStream("./core/network/booksearch/local.properties"))
}

plugins {
	id("convention.android.library")
	id("convention.android.hilt")
	alias(libs.plugins.kotlin.serialization)
}

android {
	namespace = "com.kova700.amazonbookstorepractice.core.network.booksearch"
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
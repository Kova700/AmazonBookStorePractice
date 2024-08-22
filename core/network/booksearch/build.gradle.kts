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
	implementation(project(":core:data:booksearch:external"))
	implementation(project(":core:network:network"))
	implementation(libs.bundles.ktor)
}
//드로이드 나이츠처럼 app 모듈이 모든 모듈을 참조해야하는가? (나는 의존성 주입을 위해 그런줄 알았는데 왜 드로이드 나이츠는 저런 구조로도 가능한건가)
//datastore:searchhistory는 datastore:datastore 모듈을 참조하는데 네트워크 모듈을 왜 다른가?
//datastore:datastore, network:network 모듈 처럼 해당 기능을 위한 모듈이 필요한게 맞는가
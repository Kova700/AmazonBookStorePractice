package com.kova700.amazonbookstorepractice.data.di

import android.util.Log
import com.kova700.amazonbookstorepractice.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.accept
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object KtorModule {
	private const val KAKAO_BASE_URL = "dapi.kakao.com/v3"
	private const val JSON_MEDIA_TYPE = "application/json"

	private val jsonConvertFormat = Json {
		ignoreUnknownKeys = true
		coerceInputValues = true
		encodeDefaults = true
		isLenient = true
	}

	@Singleton
	@Provides
	fun providesKtorHttpClient(
		customHttpLogger: CustomHttpLogger
	): HttpClient {
		return HttpClient(Android) {

			install(Logging) {
				logger = customHttpLogger
				level = LogLevel.ALL
			}

			install(ContentNegotiation) {
				json(jsonConvertFormat)
			}

			install(DefaultRequest) {
				header(HttpHeaders.Authorization, BuildConfig.KAKAO_REST_API_KEY)
			}

			defaultRequest {
				contentType(ContentType.Application.Json)
				accept(ContentType.Application.Json)
				url {
					host = KAKAO_BASE_URL
					protocol = URLProtocol.HTTPS
				}
			}
		}
	}

}

class CustomHttpLogger @Inject constructor() : Logger {
	override fun log(message: String) {
		Log.d("NetworkLog", message)
	}
}
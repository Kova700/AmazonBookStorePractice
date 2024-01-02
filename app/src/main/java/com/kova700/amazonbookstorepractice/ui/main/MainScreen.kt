package com.kova700.amazonbookstorepractice.ui.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.kova700.amazonbookstorepractice.ui.main.detail.DetailScreen
import com.kova700.amazonbookstorepractice.ui.main.detail.DetailWebView
import com.kova700.amazonbookstorepractice.ui.main.search.SearchScreen
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


@Composable
fun MainScreen(
	navController: NavHostController = rememberNavController(),
) {
	NavHost(navController = navController, startDestination = ScreenRoute.SEARCH.route) {

		composable(
			route = ScreenRoute.SEARCH.route,
		) {
			SearchScreen(
				navigateToDetailScreen = { itemIndex ->
					navController.navigate("${ScreenRoute.DETAIL.route}/${itemIndex}")
				}
			)
		}

		composable(
			route = "${ScreenRoute.DETAIL.route}/{$SELECTED_BOOK_INDEX}",
			arguments = listOf(
				navArgument(SELECTED_BOOK_INDEX) { type = NavType.IntType }
			)
		) {
			DetailScreen(
				navigateToWebView = { url ->
					val encodedUrl = URLEncoder.encode(url, StandardCharsets.UTF_8.toString())
					navController.navigate("${ScreenRoute.WEB_VIEW.route}/${encodedUrl}")
				}
			)
		}

		composable(
			route = "${ScreenRoute.WEB_VIEW.route}/{$DETAIL_BOOK_URL}",
			arguments = listOf(
				navArgument(DETAIL_BOOK_URL) { type = NavType.StringType }
			)
		) { navBackstackEntry ->
			DetailWebView(url = navBackstackEntry.arguments?.getString(DETAIL_BOOK_URL)!!)
		}

	}
}

const val SELECTED_BOOK_INDEX = "SELECTED_BOOK_INDEX"
const val DETAIL_BOOK_URL = "DETAIL_BOOK_URL"

enum class ScreenRoute(val route: String) {
	SEARCH("SEARCH"),
	DETAIL("DETAIL"),
	WEB_VIEW("WEB_VIEW")
}
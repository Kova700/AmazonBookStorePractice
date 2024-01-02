package com.kova700.amazonbookstorepractice.ui.main.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kova700.amazonbookstorepractice.ui.main.search.component.SearchBar
import com.kova700.amazonbookstorepractice.ui.main.search.component.SearchResult
import com.kova700.amazonbookstorepractice.ui.main.search.component.SearchResultError
import com.kova700.amazonbookstorepractice.ui.main.search.component.SearchResultLoading

@Composable
fun SearchScreen(
	searchViewModel: SearchViewModel = hiltViewModel(),
	navigateToDetailScreen: (Int) -> Unit = {},
) {

	val searchViewState by searchViewModel.viewState.collectAsStateWithLifecycle()

	SearchScreen(
		searchViewState = searchViewState,
		navigateToDetailScreen = navigateToDetailScreen,
		onValueChange = searchViewModel::changeSearchKeyword,
		onSearchClick = searchViewModel::searchKeyword
	)

}

@Composable
fun SearchScreen(
	searchViewState: SearchViewState,
	navigateToDetailScreen: (Int) -> Unit = {},
	onValueChange: (String) -> Unit,
	onSearchClick: () -> Unit,
) {
	Column(
		modifier = Modifier.fillMaxWidth()
	) {
		SearchBar(
			searchKeyword = searchViewState.searchKeyWord,
			onValueChange = onValueChange,
			onSearchClick = onSearchClick
		)

		when (searchViewState.loadState) {
			LoadState.LOADING -> {
				SearchResultLoading()
			}

			LoadState.ERROR -> {
				SearchResultError()
			}

			LoadState.SUCCESS -> {
				SearchResult(
					modifier = Modifier.fillMaxWidth(),
					onItemClick = navigateToDetailScreen,
					books = searchViewState.books,
				)
			}
		}

	}
}
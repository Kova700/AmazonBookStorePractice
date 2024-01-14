package com.kova700.amazonbookstorepractice.ui.main.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kova700.amazonbookstorepractice.ui.main.model.BookItem
import com.kova700.amazonbookstorepractice.ui.main.search.component.SearchBar
import com.kova700.amazonbookstorepractice.ui.main.search.component.SearchResult
import com.kova700.amazonbookstorepractice.ui.main.search.component.SearchResultError
import com.kova700.amazonbookstorepractice.ui.main.search.component.SearchResultLoading
import kotlinx.collections.immutable.persistentListOf

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
					uiState = searchViewState,
				)
			}
		}

	}
}

@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
	SearchScreen(
		searchViewState = SearchViewState.Default.copy(
			books = persistentListOf(
				BookItem.Default.copy(
					isbn = "1",
					title = "테스트 1"
				),
				BookItem.Default.copy(
					isbn = "2",
					title = "테스트 2"
				),
				BookItem.Default.copy(
					isbn = "3",
					title = "테스트 3"
				),
				BookItem.Default.copy(
					isbn = "4",
					title = "테스트 4"
				),
				BookItem.Default.copy(
					isbn = "5",
					title = "테스트 5"
				),
				BookItem.Default.copy(
					isbn = "6",
					title = "테스트 6"
				),
				BookItem.Default.copy(
					isbn = "7",
					title = "테스트 7"
				)
			)
		),
		navigateToDetailScreen = {},
		onValueChange = {},
		onSearchClick = {}
	)
}
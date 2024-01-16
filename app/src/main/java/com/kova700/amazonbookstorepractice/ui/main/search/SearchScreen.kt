package com.kova700.amazonbookstorepractice.ui.main.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kova700.amazonbookstorepractice.domain.model.KakaoBookSearchSortType
import com.kova700.amazonbookstorepractice.ui.main.model.BookItem
import com.kova700.amazonbookstorepractice.ui.main.search.component.SearchBar
import com.kova700.amazonbookstorepractice.ui.main.search.component.SearchResult
import com.kova700.amazonbookstorepractice.ui.main.search.component.SearchResultLoading
import com.kova700.amazonbookstorepractice.ui.main.search.component.SearchSortOptionDialog
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.launch

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
		onSearchClick = searchViewModel::searchKeyword,
		onLoadNextData = searchViewModel::loadNextData,
		onKeywordClear = searchViewModel::onKeywordClear,
		onSortOptionChange = searchViewModel::onSortOptionChange,
	)

}

@Composable
fun SearchScreen(
	searchViewState: SearchViewState,
	navigateToDetailScreen: (Int) -> Unit,
	onValueChange: (String) -> Unit,
	onSearchClick: () -> Unit,
	onLoadNextData: () -> Unit,
	onKeywordClear: () -> Unit,
	onSortOptionChange: (KakaoBookSearchSortType) -> Unit,
) {
	val scope = rememberCoroutineScope()
	val lazyGridState = rememberLazyGridState()

	var searchOptionDialogState by remember { mutableStateOf(false) }

	if (searchViewState.loadState == LoadState.LOADING) {
		SearchResultLoading()
	}

	if (searchOptionDialogState) {
		SearchSortOptionDialog(
			onChangeState = { searchOptionDialogState = false },
			onSortOptionChange = onSortOptionChange,
			currentSortType = searchViewState.sortType
		)
	}

	Column(
		modifier = Modifier.fillMaxWidth()
	) {

		SearchBar(
			searchKeyword = searchViewState.searchKeyWord,
			onValueChange = onValueChange,
			onKeywordClear = onKeywordClear,
			onSearchClick = {
				onSearchClick()
				scope.launch {
					lazyGridState.scrollToItem(0)
				}
			},
			onOptionClick = { searchOptionDialogState = true }
		)

		when (searchViewState.loadState) {
			LoadState.ERROR -> {
				Column(
					modifier = Modifier.fillMaxSize(),
					verticalArrangement = Arrangement.Center,
					horizontalAlignment = Alignment.CenterHorizontally
				) {
					Text(text = "데이터 로드를 실패했습니다.")
					Spacer(modifier = Modifier.height(16.dp))
					Button(onClick = onSearchClick) {
						Text("다시 시도")
					}
				}
			}

			LoadState.EMPTY -> {
				Column(
					modifier = Modifier.fillMaxSize(),
					verticalArrangement = Arrangement.Center,
					horizontalAlignment = Alignment.CenterHorizontally
				) {
					Text(text = "검색 결과가 없습니다.")
				}
			}

			LoadState.LOADING,
			LoadState.SUCCESS -> {
				LaunchedEffect(lazyGridState.canScrollForward.not()) {
					if (lazyGridState.canScrollForward.not() && (searchViewState.loadState == LoadState.SUCCESS)) {
						onLoadNextData()
					}
				}

				SearchResult(
					lazyGridState = lazyGridState,
					modifier = Modifier.fillMaxWidth(),
					books = searchViewState.books,
					onItemClick = navigateToDetailScreen,
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
		onSearchClick = {},
		onLoadNextData = {},
		onKeywordClear = {},
		onSortOptionChange = {},
	)
}
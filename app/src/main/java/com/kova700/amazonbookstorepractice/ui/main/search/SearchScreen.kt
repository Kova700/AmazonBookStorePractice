package com.kova700.amazonbookstorepractice.ui.main.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kova700.amazonbookstorepractice.domain.model.KakaoBookSearchSortType
import com.kova700.amazonbookstorepractice.ui.main.model.BookItem
import com.kova700.amazonbookstorepractice.ui.main.search.component.SearchBar
import com.kova700.amazonbookstorepractice.ui.main.search.component.SearchSortOptionDialog
import com.kova700.amazonbookstorepractice.ui.main.search.component.history.SearchHistoryScreen
import com.kova700.amazonbookstorepractice.ui.main.search.component.searchresult.SearchResult
import com.kova700.amazonbookstorepractice.ui.main.search.component.searchresult.SearchResultLoading
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.launch

@Composable
fun SearchScreen(
	searchViewModel: SearchViewModel = hiltViewModel(),
	navigateToDetailScreen: (Int) -> Unit,
) {
	val searchViewState by searchViewModel.viewState.collectAsStateWithLifecycle()

	SearchContent(
		searchViewState = searchViewState,
		navigateToDetailScreen = navigateToDetailScreen,
		onItemExpend = searchViewModel::onItemExpend,
		onValueChange = searchViewModel::changeSearchKeyword,
		onTextFieldFocus = searchViewModel::showHistory,
		onHistoryClick = searchViewModel::onHistoryClick,
		onSearchClick = searchViewModel::searchKeyword,
		onLoadNextData = searchViewModel::loadNextSearchResult,
		onKeywordClear = searchViewModel::onKeywordClear,
		onSortOptionChange = searchViewModel::onSortOptionChange,
	)

}

@Composable
private fun SearchContent(
	searchViewState: SearchViewState,
	navigateToDetailScreen: (Int) -> Unit,
	onItemExpend: (Int) -> Unit,
	onValueChange: (String) -> Unit,
	onTextFieldFocus: () -> Unit,
	onHistoryClick: (String) -> Unit,
	onSearchClick: () -> Unit,
	onLoadNextData: () -> Unit,
	onKeywordClear: () -> Unit,
	onSortOptionChange: (KakaoBookSearchSortType) -> Unit,
) {
	val lazyListState = rememberLazyListState()
	val focusManager = LocalFocusManager.current
	val scope = rememberCoroutineScope()
	var searchOptionDialogState by remember { mutableStateOf(false) }

	if (searchViewState.uiState == UiState.LOADING) {
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
		modifier = Modifier
			.fillMaxSize()
			.clickable(
				interactionSource = remember { MutableInteractionSource() },
				indication = null,
				onClick = { focusManager.clearFocus() }
			)
	) {

		SearchBar(
			searchKeyword = searchViewState.searchKeyWord,
			onValueChange = onValueChange,
			onTextFieldFocus = onTextFieldFocus,
			onKeywordClear = onKeywordClear,
			onSearchClick = {
				onSearchClick()
				scope.launch {
					lazyListState.scrollToItem(0)
				}
			},
			onOptionClick = { searchOptionDialogState = true }
		)

		when (searchViewState.uiState) {
			UiState.DEFAULT -> {}
			UiState.ERROR -> {
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

			UiState.EMPTY -> {
				Column(
					modifier = Modifier.fillMaxSize(),
					verticalArrangement = Arrangement.Center,
					horizontalAlignment = Alignment.CenterHorizontally
				) {
					Text(text = "검색 결과가 없습니다.")
				}
			}

			UiState.HISTORY -> {
				val rememberedOnHistoryClick = remember { onHistoryClick }
				SearchHistoryScreen(
					onHistoryClick = rememberedOnHistoryClick,
				)
			}

			UiState.LOADING,
			UiState.SUCCESS -> {
				LaunchedEffect(lazyListState.canScrollForward.not()) {
					if (lazyListState.canScrollForward.not() && (searchViewState.uiState != UiState.LOADING)) {
						onLoadNextData()
					}
				}

				SearchResult(
					lazyListState = lazyListState,
					books = searchViewState.books,
					onItemClick = navigateToDetailScreen,
					onItemExpend = onItemExpend
				)
			}
		}
	}

}

@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
	SearchContent(
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
		onItemExpend = {},
		onValueChange = {},
		onTextFieldFocus = {},
		onHistoryClick = {},
		onSearchClick = {},
		onLoadNextData = {},
		onKeywordClear = {},
		onSortOptionChange = {},
	)
}
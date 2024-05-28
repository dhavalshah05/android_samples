package com.sample.paging.recipes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.fynd.nitrozen.components.appbar.NitrozenAppBar
import com.fynd.nitrozen.theme.NitrozenTheme
import com.fynd.nitrozen.utils.extensions.clickableWithoutRipple
import com.sample.paging.R

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RecipesScreen(
    viewModel: RecipeViewModel,
    onRecipeClick: () -> Unit,
) {
    Scaffold(
        backgroundColor = NitrozenTheme.colors.background,
        topBar = {
            NitrozenAppBar(title = stringResource(R.string.app_name))
        }
    ) { paddingValues ->

        val pagingData = viewModel.recipes.collectAsLazyPagingItems()
        val isSwipeRefreshing = remember {
            mutableStateOf(false)
        }
        val pullRefreshState = rememberPullRefreshState(
            refreshing = isSwipeRefreshing.value,
            onRefresh = {
                isSwipeRefreshing.value = true
                pagingData.refresh()
            }
        )

        Box(
            modifier = Modifier
                .padding(paddingValues)
                .pullRefresh(pullRefreshState)
                .fillMaxSize(),
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
            ) {

                if (pagingData.loadState.refresh is LoadState.Loading && !isSwipeRefreshing.value) {
                    item {
                        LoadingBody()
                    }
                }

                if (pagingData.loadState.refresh is LoadState.NotLoading) {
                    isSwipeRefreshing.value = false
                    items(
                        count = pagingData.itemCount,
                        key = pagingData.itemKey { it }
                    ) {index ->

                        // This is very important. If you use peek here to get the item, loading will not work.
                        pagingData[index]?.let { item ->
                            Text(
                                text = item,
                                style = NitrozenTheme.typography.bodySmall,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clickableWithoutRipple(onRecipeClick)
                                    .padding(4.dp),
                            )
                        }
                    }
                }

                if (pagingData.loadState.append is LoadState.Loading) {
                    item {
                        LoadingFooter()
                    }
                }
            }

            PullRefreshIndicator(
                refreshing = isSwipeRefreshing.value,
                state = pullRefreshState,
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }

    }
}

@Composable
private fun LoadingBody() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun LoadingFooter() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator()
    }
}
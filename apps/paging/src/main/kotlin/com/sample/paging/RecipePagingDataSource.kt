package com.sample.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.delay

class RecipePagingDataSource : PagingSource<Int, String>() {

    override fun getRefreshKey(state: PagingState<Int, String>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, String> {
        val pageNumber = params.key ?: 1
        return try {
            delay(3000)
            LoadResult.Page(
                data = getData(page = pageNumber, items = params.loadSize),
                prevKey = if(pageNumber == 1) null else pageNumber.minus(1),
                nextKey = pageNumber.plus(1),
            )
        } catch (e: Throwable) {
            LoadResult.Error(e)
        }
    }

    private fun getData(page: Int, items: Int): List<String> {
        return mutableListOf<String>().apply {
            repeat(items) {
                add("Item: p - $page I - ${it.plus(1)}")
            }
        }
    }
}
package com.sample.paging.recipes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn

class RecipeViewModel : ViewModel() {

    val recipes by lazy {
        Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = true,
                initialLoadSize = 20
            )
        ) {
            RecipePagingDataSource()
        }.flow.cachedIn(viewModelScope)
    }
}
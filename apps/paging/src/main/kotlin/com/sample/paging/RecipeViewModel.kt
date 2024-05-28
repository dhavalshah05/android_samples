package com.sample.paging

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig

class RecipeViewModel : ViewModel() {

    val recipesFlow = Pager(
        config = PagingConfig(
            pageSize = 20,
            enablePlaceholders = true,
            initialLoadSize = 20
        )
    ) {
        RecipePagingDataSource()
    }.flow

}
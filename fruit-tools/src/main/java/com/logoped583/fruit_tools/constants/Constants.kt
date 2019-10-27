package com.logoped583.fruit_tools.constants

import androidx.paging.PagedList

private const val ITEMS_IN_PAGE: Int = 10

private const val LOAD_SIZE_HINT = 3

val listPagedConfig: PagedList.Config = PagedList.Config.Builder()
    .setPageSize(ITEMS_IN_PAGE)
    .setInitialLoadSizeHint(LOAD_SIZE_HINT)
    .setPrefetchDistance(ITEMS_IN_PAGE)
    .setEnablePlaceholders(true)
    .build()
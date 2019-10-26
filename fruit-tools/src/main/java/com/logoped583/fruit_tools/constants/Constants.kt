package com.logoped583.fruit_tools.constants

import androidx.paging.PagedList

private const val ITEMS_IN_PAGE: Int = 10

val listPagedConfig: PagedList.Config = PagedList.Config.Builder()
    .setPageSize(ITEMS_IN_PAGE)
    .setInitialLoadSizeHint(0)
    .setPrefetchDistance(ITEMS_IN_PAGE)
    .setEnablePlaceholders(false)
    .build()
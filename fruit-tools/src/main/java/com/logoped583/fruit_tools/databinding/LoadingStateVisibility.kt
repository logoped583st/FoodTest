package com.logoped583.fruit_tools.databinding

import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.logoped583.fruit_tools.LoadingStateSealed

fun LoadingStateSealed<*, *>.loadingStateVisibility(): Boolean {
    return when (this) {
        is LoadingStateSealed.Loading -> true
        else -> false
    }
}

@BindingAdapter("refreshing")
fun SwipeRefreshLayout.refresh(visibility: LoadingStateSealed<*, *>?) {
    if (visibility !is LoadingStateSealed.Refresh) {
        isRefreshing = false
    }
}
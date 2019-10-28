package com.logoped583.fruit_tools.databinding.recycler

import android.view.View
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import com.logoped583.fruit_tools.LoadingStateSealed
import com.logoped583.fruit_tools.databinding.loadingStateVisibility

@BindingAdapter("loadingBind")
fun ProgressBar.loading(visibility: LoadingStateSealed<*, *>?) {

    if (visibility?.loadingStateVisibility() == true) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}
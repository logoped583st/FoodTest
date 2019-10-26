package com.example.fruit_cusom_view.loading

import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import com.logoped583.fruit_tools.LoadingStateSealed
import com.logoped583.fruit_tools.databinding.loadingStateVisibility

@BindingAdapter("loading", "root")
fun LoadingView.visibility(visibility: LoadingStateSealed<*, *>?, rootView: ViewGroup?) {
    rootView?.apply {
        if (visibility?.loadingStateVisibility() == true) {
            show(this)
        } else {
            hide(this)
        }
    }
}
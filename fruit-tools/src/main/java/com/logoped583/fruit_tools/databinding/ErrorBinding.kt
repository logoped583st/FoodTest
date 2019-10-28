package com.logoped583.fruit_tools.databinding

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.logoped583.fruit_tools.CustomExceptions
import com.logoped583.fruit_tools.LoadingStateSealed

@BindingAdapter("error")
fun TextView.error(visibility: LoadingStateSealed<*, CustomExceptions>?) {
    if (visibility is LoadingStateSealed.Error) {
        text = visibility.error.message
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.INVISIBLE
    }
}
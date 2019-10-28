package com.logoped583.fruit_tools.databinding

import androidx.appcompat.widget.Toolbar
import androidx.databinding.BindingAdapter

@BindingAdapter("navClick")
fun Toolbar.navClick(back: () -> Unit) {
    setNavigationOnClickListener {
        back()
    }
}
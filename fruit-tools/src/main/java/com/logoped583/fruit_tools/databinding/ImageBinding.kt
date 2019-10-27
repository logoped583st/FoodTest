package com.logoped583.fruit_tools.databinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("glideSrcFitCenter")
fun ImageView.glideSrcCenterCrop(url: String) {
    Glide.with(this.context)
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .apply(RequestOptions().fitCenter())
        .into(this)
}

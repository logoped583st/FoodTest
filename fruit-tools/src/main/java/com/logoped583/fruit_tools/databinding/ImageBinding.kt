package com.logoped583.fruit_tools.databinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.logoped583.fruit_tools.R


@BindingAdapter("glideSrcFitCenter")
fun ImageView.glideSrcCenterCrop(url: String?) {
    url?.run {
        Glide.with(this@glideSrcCenterCrop)
            .load(url)
            .transition(DrawableTransitionOptions.withCrossFade(200))
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .error(R.drawable.ic_fork)
            .apply(RequestOptions().fitCenter())
            .into(this@glideSrcCenterCrop)
    }
}

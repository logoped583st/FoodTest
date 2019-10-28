package com.logoped583.fruit.localutils

import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.fruit_models_mapper.FruitDetailsDbEntity
import com.logoped583.fruit.R
import com.logoped583.fruit_tools.CustomExceptions
import com.logoped583.fruit_tools.LoadingStateSealed

/*
    TODO  WE CAN MIGRATE IT IN SEPARATE MODULE
 */
@BindingAdapter("stateText")
fun TextView.stateText(state: LoadingStateSealed<FruitDetailsDbEntity, CustomExceptions>?) {
    when (state) {
        is LoadingStateSealed.Data -> {
            text = state.data.description?.let {
                it
            } ?: let {
                setTextColor(ContextCompat.getColor(context, R.color.colorAccent))
                context.getString(R.string.no_description)
            }
        }
        is LoadingStateSealed.Error -> {
            text = state.error.message
            setTextColor(ContextCompat.getColor(context, R.color.colorAccent))
        }
    }
}
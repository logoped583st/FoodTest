package com.logoped583.fruit_tools.databinding.recycler

import androidx.recyclerview.widget.RecyclerView
import com.logoped583.fruit_tools.utils.dpToPx

fun RecyclerView.marginDecorationDp(
    height: Int?,
    width: Int?,
    spanCount: Int = 1,
    includeEdge: Boolean
) {
    this.addItemDecoration(
         MarginItemDecoration(
            this.context.dpToPx(height ?: 0),
            this.context.dpToPx(width ?: 0),
            includeEdge,
            spanCount
        )
    )
}
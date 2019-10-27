package com.logoped583.fruit_tools.databinding.recycler

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView


class MarginItemDecoration(
    private val spaceHeight: Int,
    private val spaceWidth: Int,
    private val includeEdge: Boolean,
    private val spanCount: Int
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView, state: RecyclerView.State
    ) {

        //Vertical margin
        with(outRect) {
            if (parent.getChildAdapterPosition(view) == 0) {
                top = spaceHeight
            }
            left = spaceWidth
            right = spaceWidth
            bottom = spaceHeight
        }


        val position = parent.getChildAdapterPosition(view) // item position
        val column = position % spanCount // item column

        if (includeEdge) {
            outRect.left =
                spaceWidth - column * spaceWidth / spanCount // spaceWidth - column * ((1f / spanCount) * spaceWidth)
            outRect.right =
                (column + 1) * spaceWidth / spanCount // (column + 1) * ((1f / spanCount) * spaceWidth)

            if (position < spanCount) { // top edge
                outRect.top = spaceWidth
            }
        } else {
            outRect.left = column * spaceWidth / spanCount // column * ((1f / spanCount) * spaceWidth)
            outRect.right =
                spaceWidth - (column + 1) * spaceWidth / spanCount // spaceWidth - (column + 1) * ((1f /    spanCount) * spaceWidth)
        }


    }
}
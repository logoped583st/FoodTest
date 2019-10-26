package com.logoped583.fruit_tools

import androidx.recyclerview.widget.DiffUtil

fun <D : ItemResponse> diffUtil() = object : DiffUtil.ItemCallback<D>() {
    override fun areItemsTheSame(oldItem: D, newItem: D): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: D, newItem: D): Boolean {
        return oldItem == newItem
    }
}

interface ItemResponse {
    val id: String
    override fun equals(other: Any?): Boolean
}

interface ListResponse<out T : ItemResponse> {
    val items: List<T>
}
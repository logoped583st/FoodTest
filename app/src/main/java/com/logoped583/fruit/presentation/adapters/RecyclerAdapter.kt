package com.logoped583.fruit.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.logoped583.fruit_tools.BR
import com.logoped583.fruit_tools.ItemResponse
import com.logoped583.fruit_tools.diffUtil


class RecyclerAdapter<T : ItemResponse>(@LayoutRes private val itemContainer: Int, val click: T.(View) -> Unit) :
    PagedListAdapter<T, ViewHolder<T>>(diffUtil<T>()) {


    override fun onBindViewHolder(holder: ViewHolder<T>, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder<T> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding =
            DataBindingUtil.inflate(layoutInflater, itemContainer, parent, false)

        return ViewHolder(binding, click)
    }


}


class ViewHolder<T>(private val binding: ViewDataBinding, val click: T.(View) -> Unit) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: T?) {
        binding.setVariable(BR.model, item)
        binding.executePendingBindings()
        binding.root.setOnClickListener { view ->
            item?.let {
                item.click(view)
            }
        }
    }

}
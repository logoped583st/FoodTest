package com.logoped583.fruit.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.logoped583.fruit_tools.BR
import com.logoped583.fruit_tools.ItemResponse
import com.logoped583.fruit_tools.diffUtil


class RecyclerAdapter<T : ItemResponse>(@LayoutRes private val itemContainer: Int) :
    PagedListAdapter<T, ViewHolder<T>>(diffUtil<T>()) {


    override fun onBindViewHolder(holder: ViewHolder<T>, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder<T> {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding =
            DataBindingUtil.inflate(layoutInflater, itemContainer, parent, false)

        return ViewHolder(binding)
    }

}


class ViewHolder<T>(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: T?) {
        binding.setVariable(BR._all, item)
        binding.executePendingBindings()
        binding.root.setOnClickListener { view ->
            item?.let {

            }
        }
    }

}
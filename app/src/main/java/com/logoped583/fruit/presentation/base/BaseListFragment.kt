package com.logoped583.fruit.presentation.base

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.jakewharton.rxbinding3.swiperefreshlayout.refreshes
import com.logoped583.fruit.presentation.adapters.RecyclerAdapter
import com.logoped583.fruit_tools.CustomExceptions
import com.logoped583.fruit_tools.ItemResponse
import com.logoped583.fruit_tools.ListResponse
import kotlinx.android.synthetic.main.fragment_fruit_list.*

abstract class BaseListFragment<Response : ListResponse<I>, I : ItemResponse,
        VM : BaseListLoadingViewModel<Response, I>, B : ViewDataBinding>
    : BaseBindingFragment<VM, B>() {

    private lateinit var adapter: RecyclerAdapter<I>

    abstract val itemLayout: Int

    abstract val click: I.(View) -> Unit

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = RecyclerAdapter(itemLayout, click)
        list_constraint.mBinding.rv.adapter = adapter

        disposable.add(list_constraint.mBinding.listSwiperefresh.refreshes().subscribe {
            viewModel.clearPaging()
            viewModel.refresh()
        })


        viewModel.dataSource.observe(viewLifecycleOwner, Observer(adapter::submitList))

        viewModel.state.observe(viewLifecycleOwner, Observer {
            when (it) {
                is CustomExceptions -> Snackbar.make(view, it.message, Snackbar.LENGTH_LONG).show()
            }
        })



    }

}
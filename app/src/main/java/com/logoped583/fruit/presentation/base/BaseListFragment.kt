package com.logoped583.fruit.presentation.base

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.logoped583.fruit.presentation.adapters.RecyclerAdapter
import com.logoped583.fruit_tools.ItemResponse
import com.logoped583.fruit_tools.ListResponse
import kotlinx.android.synthetic.main.fragment_fruit_list.*

abstract class BaseListFragment<R : ListResponse<I>, I : ItemResponse,
        VM : BaseListLoadingViewModel<R, I>, B : ViewDataBinding>
    : BaseBindingFragment<VM, B>() {

    private lateinit var adapter: RecyclerAdapter<I>

    abstract val itemLayout: Int

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = RecyclerAdapter(itemLayout)
        list_constraint.mBinding.rv.layoutManager = LinearLayoutManager(context)
        list_constraint.mBinding.rv.adapter = adapter
//
//        disposable.add(list_constraint.mBinding.listSwiperefresh.refreshes().subscribe {
//            viewModel.clearPaging()
//            viewModel.refresh()
//        })

        viewModel.dataSource.observe(viewLifecycleOwner, Observer(adapter::submitList))

    }

}
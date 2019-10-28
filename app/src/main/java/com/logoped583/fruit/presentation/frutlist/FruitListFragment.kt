package com.logoped583.fruit.presentation.frutlist

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.fruit_models_mapper.FruitDbEntity
import com.example.fruit_models_mapper.FruitResponse
import com.logoped583.fruit.Injectable
import com.logoped583.fruit.R
import com.logoped583.fruit.databinding.FragmentFruitListBinding
import com.logoped583.fruit.presentation.base.BaseListFragment
import kotlinx.android.synthetic.main.fragment_fruit_list.*
import javax.inject.Inject

class FruitListFragment :
    BaseListFragment<FruitResponse, FruitDbEntity, FruitListViewModel, FragmentFruitListBinding>(),
    Injectable {

    override val click: FruitDbEntity.(View) -> Unit = {
        viewModel.navigateToDetails(this)
    }

    @Inject
    override lateinit var viewModelFactory: ViewModelProvider.Factory

    override val itemLayout: Int
        get() = R.layout.fruit_item

    override val containerId: Int
        get() = R.layout.fragment_fruit_list

    override val viewModelClass: Class<FruitListViewModel>
        get() = FruitListViewModel::class.java

    override val scope: ViewModelScope
        get() = ViewModelScope.ACTIVITY

    override fun applyBinding(savedInstanceState: Bundle?) {
        binding.viewModel = viewModel
        binding.viewGroupAnim = list_constraint
    }

}
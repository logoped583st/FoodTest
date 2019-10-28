package com.logoped583.fruit.presentation.fruifdetails

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.fruit_models_mapper.FruitDbEntity
import com.logoped583.fruit.Injectable
import com.logoped583.fruit.R
import com.logoped583.fruit.databinding.FragmentFruitDetailsBinding
import com.logoped583.fruit.navigation.FruitDetailsScreen
import com.logoped583.fruit.presentation.base.BaseBindingFragment
import javax.inject.Inject

class FruitDetailsFragment :
    BaseBindingFragment<FruitDetailsViewModel, FragmentFruitDetailsBinding>(),
    Injectable {

    @Inject
    override lateinit var viewModelFactory: ViewModelProvider.Factory

    override val containerId: Int
        get() = R.layout.fragment_fruit_details

    override val viewModelClass: Class<FruitDetailsViewModel>
        get() = FruitDetailsViewModel::class.java

    override val scope: ViewModelScope
        get() = ViewModelScope.FRAGMENT

    override fun applyBinding(savedInstanceState: Bundle?) {
        fruit = arguments?.getParcelable(FruitDetailsScreen.KEY_FRUIT)
        if (savedInstanceState == null) {
            fruit?.id?.run { viewModel.getFruitDetails(this) }
        }
        binding.viewModel = viewModel
        fruit?.run { binding.model = this }
    }



    private var fruit: FruitDbEntity? = null

}

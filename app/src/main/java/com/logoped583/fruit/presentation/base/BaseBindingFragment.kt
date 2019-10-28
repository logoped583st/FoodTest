package com.logoped583.fruit.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.logoped583.fruit.R
import io.reactivex.disposables.CompositeDisposable

abstract class BaseBindingFragment<V : BaseDisposableViewModel, D : ViewDataBinding> : Fragment() {

    protected val disposable: CompositeDisposable = CompositeDisposable()

    abstract val viewModelFactory: ViewModelProvider.Factory

    abstract val containerId: Int

    protected lateinit var binding: D

    protected lateinit var viewModel: V

    abstract val viewModelClass: Class<V>

    abstract val scope: ViewModelScope

    private lateinit var snackbar: Snackbar

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = when (scope) {
            ViewModelScope.ACTIVITY -> ViewModelProviders.of(
                requireActivity(),
                viewModelFactory
            ).get(viewModelClass)
            ViewModelScope.FRAGMENT -> ViewModelProviders.of(this, viewModelFactory).get(
                viewModelClass
            )
        }

        viewModel.networkState.observe(viewLifecycleOwner, Observer {
            if (it == false) {
                snackbar.show()
            } else {
                snackbar.dismiss()
            }
        })

        applyBinding(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(layoutInflater, containerId, container, false)

        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        snackbar = Snackbar.make(
            view,
            getString(R.string.no_network),
            Snackbar.LENGTH_INDEFINITE
        )
    }

    override fun onPause() {
        snackbar.dismiss()
        super.onPause()
    }

    enum class ViewModelScope {
        ACTIVITY,
        FRAGMENT
    }

    override fun onDestroyView() {
        disposable.clear()
        super.onDestroyView()
    }

    abstract fun applyBinding(savedInstanceState: Bundle?)

}
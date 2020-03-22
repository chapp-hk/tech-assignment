package com.gojek.assignment.arch.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.gojek.assignment.BR
import com.gojek.assignment.arch.viewmodel.AbstractViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class AbstractFragment : DaggerFragment() {

    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.Factory
    protected abstract val viewModel: AbstractViewModel<out Any>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return setBinding(inflater).root
    }

    private fun setBinding(inflater: LayoutInflater): ViewDataBinding {
        return createViewDataBinding(inflater).also {
            it.setVariable(BR.viewModel, viewModel)
            it.lifecycleOwner = this
        }
    }

    protected abstract fun createViewDataBinding(inflater: LayoutInflater): ViewDataBinding
}
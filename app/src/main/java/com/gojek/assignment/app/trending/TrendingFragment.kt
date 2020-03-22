package com.gojek.assignment.app.trending

import android.view.LayoutInflater
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.viewModels
import com.gojek.assignment.arch.fragment.AbstractFragment
import com.gojek.assignment.databinding.FragmentTrendingBinding

class TrendingFragment : AbstractFragment() {

    override val viewModel: TrendingViewModel by viewModels { viewModelFactory }

    override fun createViewDataBinding(inflater: LayoutInflater): ViewDataBinding {
        return FragmentTrendingBinding.inflate(inflater)
    }
}
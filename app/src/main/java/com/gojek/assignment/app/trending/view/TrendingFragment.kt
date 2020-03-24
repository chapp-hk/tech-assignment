package com.gojek.assignment.app.trending.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.viewModels
import com.gojek.assignment.R
import com.gojek.assignment.app.trending.TrendingViewModel
import com.gojek.assignment.arch.fragment.AbstractFragment
import com.gojek.assignment.databinding.FragmentTrendingBinding

class TrendingFragment : AbstractFragment() {

    override val viewModel: TrendingViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        retainInstance = true
        viewModel.getRepos()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_trending, menu)
    }

    override fun createViewDataBinding(inflater: LayoutInflater): ViewDataBinding {
        return FragmentTrendingBinding.inflate(inflater)
    }
}
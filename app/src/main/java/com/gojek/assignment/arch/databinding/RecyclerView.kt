package com.gojek.assignment.arch.databinding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.gojek.assignment.arch.recyclerview.IViewHolder
import com.gojek.assignment.arch.recyclerview.RecyclerViewAdapter

@BindingAdapter("items")
fun initRecyclerView(recyclerView: RecyclerView, list: List<IViewHolder>?) {
    recyclerView.takeIf { it.adapter == null }?.let { _recyclerView ->
        list?.let { _recyclerView.adapter = RecyclerViewAdapter(_recyclerView.context, it) }
    }
}

@BindingAdapter("itemDecoration")
fun setItemDecoration(recyclerView: RecyclerView, orientation: Int) {
    recyclerView.addItemDecoration(DividerItemDecoration(recyclerView.context, orientation))
}
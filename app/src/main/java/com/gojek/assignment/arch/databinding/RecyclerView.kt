package com.gojek.assignment.arch.databinding

import android.graphics.drawable.Drawable
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.gojek.assignment.arch.recyclerview.IViewHolder
import com.gojek.assignment.arch.recyclerview.RecyclerViewAdapter

@BindingAdapter("items")
fun initRecyclerView(recyclerView: RecyclerView, list: List<IViewHolder>?) {
    if (null == recyclerView.adapter && null != list) {
        recyclerView.adapter = RecyclerViewAdapter(recyclerView.context, list)
    }
}

@BindingAdapter("itemDecorationOrientaion", "itemDecorationDrawable", requireAll = true)
fun setItemDecoration(recyclerView: RecyclerView, orientation: Int, drawable: Drawable) {
    recyclerView.addItemDecoration(
        DividerItemDecoration(
            recyclerView.context,
            orientation
        ).apply { setDrawable(drawable) }
    )
}
package com.gojek.assignment.arch.recyclerview

import androidx.annotation.LayoutRes

interface IViewHolder {

    @LayoutRes
    fun getLayoutId(): Int
}
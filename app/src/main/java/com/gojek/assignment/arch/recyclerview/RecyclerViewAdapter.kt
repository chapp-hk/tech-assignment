package com.gojek.assignment.arch.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.gojek.assignment.BR

class RecyclerViewAdapter(
    private val context: Context,
    private val list: List<IViewHolder>
) : RecyclerView.Adapter<RecyclerViewHolder>() {

    private val layoutInflater = LayoutInflater.from(context)

    override fun getItemViewType(position: Int): Int {
        return list[position].getLayoutId()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(getBinding(viewType, parent))
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.binding.apply {
            lifecycleOwner = context as? LifecycleOwner
            setVariable(BR.viewModel, list[position])
            executePendingBindings()
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    private fun getBinding(@LayoutRes layoutRes: Int, parent: ViewGroup): ViewDataBinding {
        return DataBindingUtil.inflate(layoutInflater, layoutRes, parent, false)
    }
}
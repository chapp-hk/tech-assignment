package com.gojek.assignment.app.trending

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gojek.assignment.R
import com.gojek.assignment.arch.recyclerview.IViewHolder
import com.gojek.domain.repo.entity.RepoEntity

class TrendingViewHolder(
    val repoEntity: RepoEntity
) : IViewHolder {

    private val _detailsVisibility = MutableLiveData(false)
    val detailVisibility: LiveData<Boolean> = _detailsVisibility

    override fun getLayoutId(): Int {
        return R.layout.item_trending
    }

    fun getAvatar(): String {
        return repoEntity.avatar
    }

    fun getAuthor(): String {
        return repoEntity.author
    }

    fun getName(): String {
        return repoEntity.name
    }

    fun toogleDetails() {
        _detailsVisibility.value?.let {
            _detailsVisibility.value = !it
        }
    }
}
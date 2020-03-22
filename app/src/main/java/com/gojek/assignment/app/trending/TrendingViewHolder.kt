package com.gojek.assignment.app.trending

import com.gojek.assignment.R
import com.gojek.assignment.arch.recyclerview.IViewHolder
import com.gojek.domain.repo.entity.RepoEntity

class TrendingViewHolder(
    private val repoEntity: RepoEntity
) : IViewHolder {

    override fun getLayoutId(): Int {
        return R.layout.item_trending
    }

    fun getAuthor(): String {
        return repoEntity.author
    }

    fun getName(): String {
        return repoEntity.name
    }
}
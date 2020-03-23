package com.gojek.assignment.app.trending

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gojek.assignment.arch.recyclerview.IViewHolder
import com.gojek.assignment.arch.viewmodel.AbstractViewModel
import com.gojek.domain.repo.entity.RepoEntity
import com.gojek.domain.repo.usecase.GetReposUseCase
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class TrendingViewModel
@Inject constructor(
    private val getReposUseCase: GetReposUseCase
) : AbstractViewModel<Any>() {

    private val _list = MutableLiveData<List<IViewHolder>>()
    val list: LiveData<List<IViewHolder>> = _list

    fun refresh() {

    }

    fun getRepos() {
        getReposUseCase.execute()
            .map(this::mapToTrendingViewHolder)
            .subscribe(this::onGetRepoSuccess, this::onGetRepoError)
            .addTo(compositeDisposable)
    }

    private fun mapToTrendingViewHolder(repoList: List<RepoEntity>): List<TrendingViewHolder> {
        return repoList.map {
            TrendingViewHolder(it)
        }
    }

    private fun onGetRepoSuccess(viewHolderList: List<TrendingViewHolder>) {
        _list.value = viewHolderList
    }

    private fun onGetRepoError(throwable: Throwable) {

    }
}
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

    private val _refreshing = MutableLiveData(false)
    val refreshing: LiveData<Boolean> = _refreshing

    fun refresh() {
        fetchRepos(true)
    }

    fun getRepos() {
        fetchRepos(false)
    }

    private fun fetchRepos(shouldForceUpdate: Boolean) {
        getReposUseCase.execute(shouldForceUpdate)
            .doOnSubscribe { setLoadingStatus(shouldForceUpdate) }
            .doFinally { resetLoadingStatus() }
            .map(this::mapToTrendingViewHolder)
            .subscribe(this::onGetRepoSuccess, this::onGetRepoError)
            .addTo(compositeDisposable)
    }

    private fun setLoadingStatus(shouldForceUpdate: Boolean) {
        _refreshing.value = shouldForceUpdate
    }

    private fun resetLoadingStatus() {
        _refreshing.value = false
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
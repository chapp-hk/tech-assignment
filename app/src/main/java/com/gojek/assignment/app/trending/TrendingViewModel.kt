package com.gojek.assignment.app.trending

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gojek.assignment.arch.recyclerview.IViewHolder
import com.gojek.assignment.arch.viewmodel.AbstractViewModel
import com.gojek.domain.repo.entity.RepoEntity
import com.gojek.domain.repo.usecase.GetReposUseCase
import io.reactivex.rxkotlin.addTo
import timber.log.Timber
import javax.inject.Inject

class TrendingViewModel
@Inject constructor(
    private val getReposUseCase: GetReposUseCase
) : AbstractViewModel() {

    private val _list = MutableLiveData<List<IViewHolder>>()
    val list: LiveData<List<IViewHolder>> = _list

    private val _refreshing = MutableLiveData(false)
    val refreshing: LiveData<Boolean> = _refreshing

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData(false)
    val error: LiveData<Boolean> = _error

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
        _loading.value = !shouldForceUpdate
        _error.value = false
    }

    private fun resetLoadingStatus() {
        _refreshing.value = false
        _loading.value = false
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
        Timber.e(throwable)

        if (list.value.isNullOrEmpty()) {
            _error.value = true
        }
    }
}
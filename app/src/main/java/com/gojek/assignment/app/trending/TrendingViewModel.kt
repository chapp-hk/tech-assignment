package com.gojek.assignment.app.trending

import com.gojek.assignment.arch.viewmodel.AbstractViewModel
import com.gojek.domain.repo.usecase.GetReposUseCase
import javax.inject.Inject

class TrendingViewModel
@Inject constructor(
    private val getReposUseCase: GetReposUseCase
): AbstractViewModel<Any>() {

    fun getRepos() {
        getReposUseCase.execute()
            .subscribe()
    }
}
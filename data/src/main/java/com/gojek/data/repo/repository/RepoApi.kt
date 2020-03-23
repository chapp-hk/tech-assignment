package com.gojek.data.repo.repository

import com.gojek.data.repo.entity.RepoData
import io.reactivex.Single
import retrofit2.http.GET

interface RepoApi {

    @GET("repositories")
    fun getRepos(): Single<List<RepoData>>
}
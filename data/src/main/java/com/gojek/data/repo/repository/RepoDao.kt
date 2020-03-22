package com.gojek.data.repo.repository

import androidx.room.Dao
import androidx.room.Query
import com.gojek.data.repo.entity.RepoData
import io.reactivex.Single

@Dao
interface RepoDao {

    @Query("SELECT * FROM repo")
    fun getRepos(): Single<List<RepoData>>

    @Query("DELETE FROM repo")
    fun deleteAllRepos()
}
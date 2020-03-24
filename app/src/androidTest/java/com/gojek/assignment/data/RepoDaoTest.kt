package com.gojek.assignment.data

import com.gojek.data.repo.entity.RepoData
import com.gojek.data.repo.repository.RepoDao
import org.junit.Before
import org.junit.Test

class RepoDaoTest : AbstractDaoTest() {

    private lateinit var repoDao: RepoDao

    @Before
    override fun setUp() {
        super.setUp()
        repoDao = daoProvider.getRepoDao()
    }

    @Test
    fun insert_get_delete_test() {
        val repoDataList = listOf(RepoData())

        repoDao.insertRepos(repoDataList)

        repoDao.getRepos()
            .test()
            .assertValue { it == repoDataList }

        repoDao.deleteAllRepos()

        repoDao.getRepos()
            .test()
            .assertValue { it.isEmpty() }
    }
}
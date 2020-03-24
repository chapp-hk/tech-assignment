package com.gojek.data.repo.repository

import com.gojek.data.local.IAppPreference
import com.gojek.data.repo.entity.RepoData
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class RepoRepositoryTest {

    @MockK
    private lateinit var repoApi: RepoApi

    @MockK
    private lateinit var repoDao: RepoDao

    @MockK
    private lateinit var appPreference: IAppPreference


    private lateinit var singleRepoData: Single<List<RepoData>>
    private lateinit var repoRepository: RepoRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        singleRepoData = Single.just(listOf(RepoData()))
        repoRepository = RepoRepository(repoApi, repoDao, appPreference)

        every { repoApi.getRepos() } returns singleRepoData
        every { repoDao.getRepos() } returns singleRepoData
        every { repoDao.deleteAllRepos() } returns Unit
        every { repoDao.insertRepos(any()) } returns Unit
    }

    @Test
    fun `getRepos should only fetch from network when shouldForceUpdate is true`() {
        every { appPreference.isDataExpired() } returns false

        repoRepository.getRepos(true).test()

        verify { repoApi.getRepos() }
        verify { repoDao.deleteAllRepos() }
        verify { repoDao.insertRepos(any()) }
        verify { appPreference.setDataTime(any()) }
        verify(exactly = 0) { repoDao.getRepos() }
    }

    @Test
    fun `getRepos should only fetch from network when data is expired`() {
        every { appPreference.isDataExpired() } returns true

        repoRepository.getRepos(false).test()

        verify { repoApi.getRepos() }
        verify(exactly = 0) { repoDao.getRepos() }
    }

    @Test
    fun `getRepos should not fetch from network if local store is not empty`() {
        every { appPreference.isDataExpired() } returns false

        repoRepository.getRepos(false).test()

        verify { repoDao.getRepos() }
        verify(exactly = 0) { repoDao.deleteAllRepos() }
        verify(exactly = 0) { repoDao.insertRepos(any()) }
        verify(exactly = 0) { appPreference.setDataTime(any()) }
    }

    @Test
    fun `getRepos should fetch from network if local store is empty`() {
        every { appPreference.isDataExpired() } returns false
        every { repoDao.getRepos() } returns Single.just(listOf())

        repoRepository.getRepos(false).test()

        verify { repoDao.deleteAllRepos() }
        verify { repoDao.insertRepos(any()) }
        verify { appPreference.setDataTime(any()) }
    }
}
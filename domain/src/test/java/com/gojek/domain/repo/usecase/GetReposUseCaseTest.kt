package com.gojek.domain.repo.usecase

import com.gojek.domain.repo.IRepoRepository
import com.gojek.domain.repo.entity.RepoEntity
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.reactivex.Single
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test

class GetReposUseCaseTest {

    @MockK
    private lateinit var mainScheduler: TestScheduler
    @MockK
    private lateinit var ioScheduler: TestScheduler
    @MockK
    private lateinit var repoRepository: IRepoRepository
    @MockK
    private lateinit var single: Single<List<RepoEntity>>

    private lateinit var getReposUseCase: GetReposUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        getReposUseCase = GetReposUseCase(mainScheduler, ioScheduler, repoRepository)
    }

    @Test
    fun execute() {
        every { repoRepository.getRepos(any()) } returns single
        every { single.subscribeOn(any()) } returns single
        every { single.observeOn(any()) } returns single

        getReposUseCase.execute(true)

        verify { repoRepository.getRepos(eq(true)) }
        verify { single.subscribeOn(eq(ioScheduler)) }
        verify { single.observeOn(eq(mainScheduler)) }
    }
}
package com.gojek.assignment.app.trending

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.gojek.domain.repo.entity.RepoEntity
import com.gojek.domain.repo.usecase.GetReposUseCase
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class TrendingViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @MockK
    private lateinit var getReposUseCase: GetReposUseCase

    private lateinit var trendingViewModel: TrendingViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        trendingViewModel = TrendingViewModel(getReposUseCase)
    }

    @Test
    fun `refresh should set items in list when success`() {
        every { getReposUseCase.execute(any()) } returns Single.just(listOf(RepoEntity()))

        trendingViewModel.refresh()

        verify { getReposUseCase.execute(eq(true)) }
        assertFalse(trendingViewModel.error.value!!)
        assertFalse(trendingViewModel.list.value.isNullOrEmpty())
    }

    @Test
    fun `refresh should set error to true when error`() {
        every { getReposUseCase.execute(any()) } returns Single.error(Throwable())

        trendingViewModel.refresh()

        assertTrue(trendingViewModel.error.value!!)
        assertTrue(trendingViewModel.list.value.isNullOrEmpty())
    }

    @Test
    fun `getRepos should execute use case with parameter false`() {
        every { getReposUseCase.execute(any()) } returns Single.just(listOf(RepoEntity()))

        trendingViewModel.getRepos()

        verify { getReposUseCase.execute(eq(false)) }
    }
}
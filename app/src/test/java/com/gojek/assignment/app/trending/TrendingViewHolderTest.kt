package com.gojek.assignment.app.trending

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.gojek.assignment.R
import com.gojek.domain.repo.entity.RepoEntity
import io.mockk.every
import io.mockk.spyk
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class TrendingViewHolderTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val repoEntity = spyk(RepoEntity())
    private lateinit var trendingViewHolder: TrendingViewHolder

    @Before
    fun setUp() {
        trendingViewHolder = TrendingViewHolder(repoEntity)
    }

    @Test
    fun getLayoutId() {
        assertEquals(R.layout.item_trending, trendingViewHolder.getLayoutId())
    }

    @Test
    fun toogleDetails() {
        assertEquals(false, trendingViewHolder.detailVisibility.value)

        trendingViewHolder.toogleDetails()

        assertEquals(true, trendingViewHolder.detailVisibility.value)
    }

    @Test
    fun `get LanguageVisibility return true when language is not empty`() {
        every { repoEntity.language } returns "some language"

        assertTrue(trendingViewHolder.getLanguageVisibility())
    }

    @Test
    fun `get LanguageVisibility return false when language is empty`() {
        every { repoEntity.language } returns ""

        assertFalse(trendingViewHolder.getLanguageVisibility())
    }

    @Test
    fun getRepoEntity() {
        assertEquals(repoEntity, trendingViewHolder.repoEntity)
    }
}
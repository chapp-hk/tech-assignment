package com.gojek.data.repo.entity

import org.junit.Assert.assertEquals
import org.junit.Test

class RepoDataMapperTest {

    private val repoDataMapper = RepoDataMapper()

    @Test
    fun mapToEntity() {
        val data = RepoData(
            author = "some auther",
            name = "some name",
            avatar = "some avatar",
            url = "some url",
            description = "some description",
            language = "some language",
            languageColor = 4567,
            stars = "1,213",
            forks = "6,578"
        )

        repoDataMapper.mapToEntity(data)

        assertEquals("some auther", data.author)
        assertEquals("some name", data.name)
        assertEquals("some avatar", data.avatar)
        assertEquals("some url", data.url)
        assertEquals("some description", data.description)
        assertEquals("some language", data.language)
        assertEquals(4567, data.languageColor)
        assertEquals("1,213", data.stars)
        assertEquals("6,578", data.forks)
    }
}
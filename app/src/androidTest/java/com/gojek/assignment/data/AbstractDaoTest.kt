package com.gojek.assignment.data

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.gojek.assignment.arch.helper.DaoProvider
import org.junit.After
import org.junit.Before

abstract class AbstractDaoTest {

    protected lateinit var daoProvider: DaoProvider

    @Before
    open fun setUp() {
        daoProvider = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().targetContext.applicationContext,
            DaoProvider::class.java
        ).build()
    }

    @After
    fun tearDown() {
        daoProvider.close()
    }
}
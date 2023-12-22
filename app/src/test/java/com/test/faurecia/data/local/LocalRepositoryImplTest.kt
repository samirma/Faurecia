package com.test.faurecia.data.local

import com.appmattus.kotlinfixture.Fixture
import com.test.faurecia.data.local.model.App
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class LocalRepositoryImplTest {

    private val appItemDao: AppItemDao = mockk()

    lateinit var localRepositoryImpl: LocalRepositoryImpl

    private val fixture = Fixture()

    @Before
    fun setup() {
        localRepositoryImpl = LocalRepositoryImpl(appItemDao)
    }

    @Test
    fun `getAppById returns expected result`() = runTest {
        val expected = fixture<App>()
        val appId = expected.id
        coEvery { appItemDao.getAppById(appId) } returns expected

        val result = localRepositoryImpl.getAppById(appId)

        assertEquals(expected, result)
    }

    @Test
    fun `getAppList returns expected result`() = runTest {
        val expected = fixture<List<App>>()
        coEvery { appItemDao.getAppList() } returns flowOf(expected)

        val result = localRepositoryImpl.getAppList().first()

        assertEquals(expected, result)
    }

    @Test
    fun `saveList saves expected list`() = runTest {
        val expected = fixture<List<App>>()
        coEvery { appItemDao.insertAll(expected) } returns Unit

        localRepositoryImpl.saveList(expected)

        // Verify that the insertAll method was called with the expected list
        coVerify { appItemDao.insertAll(expected) }
    }
}

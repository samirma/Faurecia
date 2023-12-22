package com.test.faurecia.userCases

import org.junit.Assert.*

import com.appmattus.kotlinfixture.Fixture
import com.test.faurecia.data.local.LocalRepository
import com.test.faurecia.data.local.model.App
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GetAppByIdUseCaseTest {

    private val repository: LocalRepository = mockk()

    lateinit var getAppByIdUseCase: GetAppByIdUseCase

    private val fixture = Fixture()

    @Before
    fun setup() {
        getAppByIdUseCase = GetAppByIdUseCase(repository)
    }

    @Test
    fun `invoke returns expected result`() = runTest {
        val expected = fixture<App>()
        val appId = expected.id
        coEvery { repository.getAppById(appId) } returns expected

        val result = getAppByIdUseCase(appId)

        assertEquals(expected, result.getOrThrow())
    }

    @Test
    fun `invoke throws exception`() = runTest {
        val exception = RuntimeException("Database error")
        val appId = fixture<String>()
        coEvery { repository.getAppById(appId) } throws exception

        val result = getAppByIdUseCase(appId)

        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
    }
}

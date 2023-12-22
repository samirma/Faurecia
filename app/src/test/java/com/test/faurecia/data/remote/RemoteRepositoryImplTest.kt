package com.test.faurecia.data.remote


import com.appmattus.kotlinfixture.Fixture
import com.test.faurecia.data.remote.model.ListAppsResponseDTO
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class RemoteRepositoryImplTest {

    private val apiService: ApiService = mockk()

    lateinit var remoteRepositoryImpl: RemoteRepository

    private val fixture = Fixture()

    @Before
    fun setup() {
        remoteRepositoryImpl = RemoteRepositoryImpl(apiService)
    }

    @Test
    fun `getApps returns expected result`() = runTest {
        val expected = fixture<ListAppsResponseDTO>()
        coEvery { apiService.listApps() } returns expected

        val result = remoteRepositoryImpl.getApps()

        assertEquals(expected, result.getOrThrow())
    }

    @Test
    fun `getApps throws exception`() = runTest {
        val exception = RuntimeException("Network error")
        coEvery { apiService.listApps() } throws exception

        val result = remoteRepositoryImpl.getApps()

        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
    }
}

package com.test.faurecia.userCases

import com.appmattus.kotlinfixture.Fixture
import com.test.faurecia.data.local.model.App
import com.test.faurecia.data.remote.RemoteRepository
import com.test.faurecia.data.remote.model.ListAppsResponseDTO
import com.test.faurecia.userCases.model.AppItemMapper
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GetRemoteAppsListUseCaseTest {

    private val remoteRepository: RemoteRepository = mockk()
    private val mapper: AppItemMapper = mockk()

    lateinit var getRemoteAppsListUseCase: GetRemoteAppsListUseCase

    private val fixture = Fixture()

    @Before
    fun setup() {
        getRemoteAppsListUseCase = GetRemoteAppsListUseCase(remoteRepository, mapper)
    }

    @Test
    fun `invoke returns expected list`() = runTest {
        val expectedDTO = fixture<ListAppsResponseDTO>()
        val expectedAppList = fixture<List<App>>()
        coEvery { remoteRepository.getApps() } returns Result.success(expectedDTO)
        coEvery { mapper(expectedDTO) } returns expectedAppList

        val result = getRemoteAppsListUseCase()

        assertEquals(Result.success(expectedAppList), result)
    }

    @Test
    fun `invoke throws exception`() = runTest {
        val exception = Error("Network error")
        coEvery { remoteRepository.getApps() } returns Result.failure(exception)

        val result = getRemoteAppsListUseCase()

        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
    }
}

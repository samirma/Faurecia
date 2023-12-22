package com.test.faurecia.userCases

import com.appmattus.kotlinfixture.Fixture
import com.test.faurecia.data.local.model.App
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GetAppsListUseCaseTest {

    private val getLocalAppsListUseCase: GetLocalAppsListUseCase = mockk()
    private val getRemoteAppsListUseCase: GetRemoteAppsListUseCase = mockk()
    private val saveAppListUseCase: SaveAppListUseCase = mockk(relaxed = true)

    lateinit var getAppsListUseCase: GetAppsListUseCase

    private val fixture = Fixture()

    @Before
    fun setup() {
        getAppsListUseCase = GetAppsListUseCase(
            getLocalAppsListUseCase = getLocalAppsListUseCase,
            getRemoteAppsListUseCase = getRemoteAppsListUseCase,
            saveAppListUseCase = saveAppListUseCase
        )
    }

    @Test
    fun `invoke returns local list when not empty`() = runTest {
        val expected = fixture<List<App>>()
        coEvery { getLocalAppsListUseCase() } returns flowOf(expected)

        val result = getAppsListUseCase().toList().flatten()

        assertEquals(expected, result)

        // Verify that the local repository was queried
        coVerify { getLocalAppsListUseCase() }
        // Verify that the remote repository was not queried
        coVerify(exactly = 0) { getRemoteAppsListUseCase() }
        // Verify that no data was saved to the local repository
        coVerify(exactly = 0) { saveAppListUseCase(any()) }
    }

    @Test
    fun `invoke returns remote list when local list is empty`() = runTest {
        val expected = fixture<List<App>>()
        coEvery { getLocalAppsListUseCase() } returns flowOf(emptyList(), fixture<List<App>>())
        coEvery { getRemoteAppsListUseCase() } returns Result.success(expected)

        getAppsListUseCase().toList()

        // Verify that the local repository was queried
        coVerify { getLocalAppsListUseCase() }
        // Verify that the remote repository was queried
        coVerify { getRemoteAppsListUseCase() }
        // Verify that the data was saved to the local repository
        coVerify { saveAppListUseCase(expected) }
    }
}

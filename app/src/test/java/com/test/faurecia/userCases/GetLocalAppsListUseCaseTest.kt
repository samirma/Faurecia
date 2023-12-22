package com.test.faurecia.userCases

import com.appmattus.kotlinfixture.Fixture
import com.test.faurecia.data.local.LocalRepository
import com.test.faurecia.data.local.model.App
import io.mockk.coEvery
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
class GetLocalAppsListUseCaseTest {

    private val localRepository: LocalRepository = mockk()
    private val fixture = Fixture()

    lateinit var getLocalAppsListUseCase: GetLocalAppsListUseCase

    @Before
    fun setup() {
        getLocalAppsListUseCase = GetLocalAppsListUseCase(localRepository)
    }

    @Test
    fun `invoke returns expected result`() = runTest {
        val expected = fixture<List<App>>()
        coEvery { localRepository.getAppList() } returns flowOf(expected)

        val actual = getLocalAppsListUseCase().first()

        assertEquals(expected, actual)
    }
}

package com.test.faurecia.userCases

import com.appmattus.kotlinfixture.Fixture
import com.test.faurecia.data.local.LocalRepository
import com.test.faurecia.data.local.model.App
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class SaveAppListUseCaseTest {

    private val repository: LocalRepository = mockk(relaxed = true)

    lateinit var saveAppListUseCase: SaveAppListUseCase

    private val fixture = Fixture()

    @Before
    fun setup() {
        saveAppListUseCase = SaveAppListUseCase(repository)
    }

    @Test
    fun `invoke saves expected list`() = runTest {
        val expected = fixture<List<App>>()

        saveAppListUseCase(expected)

        // Verify that the saveList method was called with the expected list
        coVerify { repository.saveList(expected) }
    }
}

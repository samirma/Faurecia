package com.test.faurecia.feature.detail

import com.appmattus.kotlinfixture.Fixture
import com.test.faurecia.MainCoroutineRule
import com.test.faurecia.data.local.model.App
import com.test.faurecia.feature.detail.model.AppDetailMapper
import com.test.faurecia.feature.detail.model.AppDetailView
import com.test.faurecia.feature.detail.model.DetailState
import com.test.faurecia.userCases.GetAppByIdUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class AppDetailViewModelTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val getAppById: GetAppByIdUseCase = mockk()
    private val mapper: AppDetailMapper = mockk()

    private lateinit var appDetailViewModel: AppDetailViewModel

    private val fixture = Fixture()

    @Before
    fun setup() {
        appDetailViewModel = AppDetailViewModel(getAppById, mapper)
    }

    @Test
    fun `fetchApp returns expected result`() = runTest {
        val expectedApp = fixture<App>()
        val expectedAppDetailView = fixture<AppDetailView>()
        val appId = expectedApp.id
        coEvery { getAppById(appId) } returns Result.success(expectedApp)
        coEvery { mapper(expectedApp) } returns expectedAppDetailView

        appDetailViewModel.fetchApp(appId)

        val result = appDetailViewModel.state.first()

        assertEquals(DetailState.Loaded(expectedAppDetailView), result)
    }

    @Test
    fun `fetchApp throws exception`() = runTest {

        val appId = fixture<String>()
        coEvery { getAppById(appId) } returns Result.failure(RuntimeException("Network error"))

        appDetailViewModel.fetchApp(appId)

        val result = appDetailViewModel.state.value

        assertEquals(DetailState.Error, result)
    }
}

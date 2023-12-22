package com.test.faurecia.feature.list

import com.appmattus.kotlinfixture.Fixture
import com.test.faurecia.MainCoroutineRule
import com.test.faurecia.data.local.model.App
import com.test.faurecia.feature.list.model.AppItemView
import com.test.faurecia.feature.list.model.AppItemViewMapper
import com.test.faurecia.feature.list.model.ListState
import com.test.faurecia.userCases.GetAppsListUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ListViewModelTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val getAppsListUseCase: GetAppsListUseCase = mockk()
    private val appItemViewMapper: AppItemViewMapper = mockk()
    private val fixture = Fixture()

    lateinit var listViewModel: ListViewModel

    @Before
    fun setup() {
        listViewModel = ListViewModel(getAppsListUseCase, appItemViewMapper)
    }

    @Test
    fun `fetchAppsList returns expected result`() = runTest {
        val appList = fixture<List<App>>()
        val appItemViewList = fixture<List<AppItemView>>()
        val expected = ListState.Loaded(appItemViewList)
        coEvery { getAppsListUseCase() } returns flowOf(appList)
        coEvery { appItemViewMapper(appList) } returns appItemViewList

        listViewModel.fetchAppsList()

        assertEquals(expected, listViewModel.state.value)
    }

    @Test
    fun `fetchAppsList throws exception`() = runTest {
        val exception = Exception("Network error")
        coEvery { getAppsListUseCase() } throws exception

        listViewModel.fetchAppsList()

        assertTrue(listViewModel.state.value is ListState.Error)
    }
}

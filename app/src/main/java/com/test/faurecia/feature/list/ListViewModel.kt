package com.test.faurecia.feature.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.faurecia.feature.list.model.AppItemViewMapper
import com.test.faurecia.feature.list.model.ListState
import com.test.faurecia.userCases.GetAppsListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getAppsList: GetAppsListUseCase,
    private val map: AppItemViewMapper
) : ViewModel() {

    private val _state = MutableStateFlow<ListState>(ListState.Loading)
    val state: StateFlow<ListState> get() = _state

    fun fetchAppsList() = viewModelScope.launch {
        _state.value = ListState.Loading
        try {
            val appList = getAppsList()
            _state.value = ListState.ListViewModelState(map(appList))
        } catch (e: Exception) {
            _state.value = ListState.Error
        }
    }
}

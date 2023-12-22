package com.test.faurecia.feature.list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.faurecia.feature.list.model.AppItemViewMapper
import com.test.faurecia.feature.list.model.ListState
import com.test.faurecia.feature.list.model.ListState.Loaded
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

    companion object {
        private val TAG = ListViewModel::class.java.simpleName
    }

    private val _state = MutableStateFlow<ListState>(ListState.Loading)
    val state: StateFlow<ListState> get() = _state

    fun fetchAppsList() = viewModelScope.launch {
        try {
            getAppsList().collect { appList ->
                _state.value = Loaded(map(appList))
            }
        } catch (e: Exception) {
            Log.e(TAG, e.message, e)
            _state.value = ListState.Error
        }
    }

}

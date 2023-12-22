package com.test.faurecia.feature.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.faurecia.feature.detail.model.AppDetailMapper
import com.test.faurecia.feature.detail.model.DetailState
import com.test.faurecia.feature.detail.model.DetailState.Loaded
import com.test.faurecia.feature.detail.model.DetailState.Loading
import com.test.faurecia.userCases.GetAppByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppDetailViewModel @Inject constructor(
    private val getAppById: GetAppByIdUseCase,
    private val mapper: AppDetailMapper,
) : ViewModel() {

    companion object {
        private val TAG = AppDetailViewModel::class.java.simpleName
    }

    private val _state = MutableStateFlow<DetailState>(Loading)
    val state: StateFlow<DetailState> get() = _state

    fun fetchApp(appId: String) = viewModelScope.launch {
        try {
            val app = getAppById(appId)
            _state.value = Loaded(mapper(app))
        } catch (e: Exception) {
            Log.e(TAG, e.message, e)
            _state.value = DetailState.Error
        }
    }
}

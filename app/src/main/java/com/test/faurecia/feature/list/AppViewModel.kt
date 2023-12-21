package com.test.faurecia.feature.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.faurecia.data.remote.AppRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    private val repository: AppRepositoryImpl
) : ViewModel() {
    fun test() {
        viewModelScope.launch {
            repository.getApps()
        }
    }

}
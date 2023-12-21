package com.test.faurecia.feature.list

import androidx.lifecycle.ViewModel
import com.test.faurecia.data.remote.AppRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    private val repository: AppRepositoryImpl
) : ViewModel() {

}
package com.test.faurecia.feature.list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.faurecia.data.remote.Repository
import com.test.faurecia.userCases.getAppsListUserCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getAppsList: getAppsListUserCase
) : ViewModel() {

}
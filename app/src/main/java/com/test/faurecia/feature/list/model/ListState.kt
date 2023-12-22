package com.test.faurecia.feature.list.model

sealed class ListState {

    data object Error : ListState()

    data object Loading : ListState()

    data class Loaded(
        val appList: List<AppItemView>
    ) : ListState()

}
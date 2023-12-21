package com.test.faurecia.feature.list.model

sealed class ListState() {

    data object Error : ListState()

    data object Loading : ListState()

    data class ListViewModelState(
        val appList: List<AppItemView>
    ) : ListState()

}
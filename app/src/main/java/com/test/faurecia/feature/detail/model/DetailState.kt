package com.test.faurecia.feature.detail.model

sealed class DetailState() {

    data object Error : DetailState()

    data object Loading : DetailState()

    data class Loaded(
        val app: AppDetailView
    ) : DetailState()

}
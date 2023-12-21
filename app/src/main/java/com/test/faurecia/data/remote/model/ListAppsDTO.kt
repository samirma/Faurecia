package com.test.faurecia.data.remote.model


import com.google.gson.annotations.SerializedName

data class ListAppsDTO(
    @SerializedName("datasets")
    val datasets: DatasetsDTO,
    @SerializedName("info")
    val info: InfoDTOX
)
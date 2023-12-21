package com.test.faurecia.data.remote.model


import com.google.gson.annotations.SerializedName

data class DatasetsDTO(
    @SerializedName("all")
    val all: AllDTO
)
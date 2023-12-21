package com.test.faurecia.data.remote.model


import com.google.gson.annotations.SerializedName

data class AllDTO(
    @SerializedName("data")
    val `data`: DataDTO,
    @SerializedName("info")
    val info: InfoDTOX
)
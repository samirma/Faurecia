package com.test.faurecia.data.remote.model


import com.google.gson.annotations.SerializedName

data class DataDTO(
    @SerializedName("hidden")
    val hidden: Int,
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("list")
    val list: DTOLDTOiDTOsDTOtDTO<DTO>DTO,
    @SerializedName("next")
    val next: Int,
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("total")
    val total: Int
)
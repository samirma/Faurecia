package com.test.faurecia.data.remote.model


import com.google.gson.annotations.SerializedName

data class ListAppsResponseDTO(
    @SerializedName("responses")
    val responses: ResponsesDTO,
    @SerializedName("status")
    val status: String
)
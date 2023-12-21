package com.test.faurecia.data.remote.model


import com.google.gson.annotations.SerializedName

data class ResponsesDTO(
    @SerializedName("listApps")
    val listApps: ListAppsDTO
)
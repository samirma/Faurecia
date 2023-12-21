package com.test.faurecia.data.remote.model


import com.google.gson.annotations.SerializedName

data class InfoDTOX(
    @SerializedName("status")
    val status: String,
    @SerializedName("time")
    val time: TimeDTO
)
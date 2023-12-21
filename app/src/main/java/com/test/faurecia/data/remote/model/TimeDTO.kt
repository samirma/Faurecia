package com.test.faurecia.data.remote.model


import com.google.gson.annotations.SerializedName

data class TimeDTO(
    @SerializedName("human")
    val human: String,
    @SerializedName("seconds")
    val seconds: Double
)
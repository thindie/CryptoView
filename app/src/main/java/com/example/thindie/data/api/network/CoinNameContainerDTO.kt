package com.example.thindie.data.api.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinNameContainerDTO (
    @SerializedName("Name")
    @Expose
    val name: String? = null
)

package com.example.thindie.data.api.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RawDataToCoinInfoDTO (
    @SerializedName("CoinInfo")
    @Expose
    val coinNameContainerDTO: CoinNameContainerDTO? = null
)

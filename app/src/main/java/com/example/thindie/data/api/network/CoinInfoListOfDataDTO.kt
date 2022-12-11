package com.example.thindie.data.api.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinInfoListOfDataDTO (
    @SerializedName("Data")
    @Expose
    val data: List<RawDataToCoinInfoDTO>? = null
)

package com.example.thindie.data.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RawDataToCoinInfo (
    @SerializedName("CoinInfo")
    @Expose
    val coinInfoToName: CoinInfoToName? = null
)

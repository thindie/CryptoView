package com.example.thindie.data.api.network

import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinJsonObjectDTO (
    @SerializedName("RAW")
    @Expose
    val json: JsonObject? = null
)

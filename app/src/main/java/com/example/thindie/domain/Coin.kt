package com.example.thindie.domain

import android.graphics.Bitmap
import android.media.Image
import android.media.ImageReader
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Coin(
    val id: Int?,
    val type: String?,
    val market: String?,
    val fromSymbol: String,
    val toSymbol: String?,
    val flags: String?,
    val price: String?,
    val lastUpdate: String?,
    val lastVolume: String?,
    val lastVolumeTo: String?,
    val lastTradeId: String?,
    val volumeDay: String?,
    val volumeDayTo: String?,
    val volume24Hour: String?,
    val volume24HourTo: String?,
    val openDay: String?,
    val highDay: String?,
    val lowDay: String?,
    val open24Hour: String?,
    val high24Hour: String?,
    val low24Hour: String?,
    val lastMarket: String?,
    val volumeHour: String?,
    val volumeHourTo: String?,
    val openHour: String?,
    val highHour: String?,
    val lowHour: String?,
    val topTierVolume24Hour: String?,
    val topTierVolume24HourTo: String?,
    val change24Hour: String?,
    val changePCT24Hour: String?,
    val changeDay: String?,
    val changePCTDay: String?,
    val supply: String?,
    val mktCap: String?,
    val totalVolume24Hour: String?,
    val totalVolume24HourTo: String?,
    val totalTopTierVolume24Hour: String?,
    val totalTopTierVolume24HourTo: String?,
    val imageUrl: String?,


) : Parcelable {

}

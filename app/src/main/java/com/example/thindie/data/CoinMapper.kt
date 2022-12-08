package com.example.thindie.data

import com.example.thindie.data.api.RetrofitApiFactory
import com.example.thindie.domain.Coin
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

class CoinMapper {

   private suspend fun coinDBModelToCoin(coinDBModel: CoinDBModel): Coin {

        return Coin(
            id = ID++,
            type = coinDBModel.type.toString(),
            market = coinDBModel.market.toString(),
            price = coinDBModel.price.toString(),
            lastUpdate =  coinDBModel.lastUpdate.toString(),
            lastVolume = coinDBModel.lastVolume.toString(),
            lastVolumeTo = coinDBModel.lastVolumeTo.toString(),
            flags = coinDBModel.flags.toString(),
            lastTradeId = coinDBModel.lastTradeId.toString(),
            volumeDay = coinDBModel.volumeDay.toString(),
            volumeDayTo = coinDBModel.volumeDayTo.toString(),
            volume24Hour = coinDBModel.volume24Hour.toString(),
            volume24HourTo = coinDBModel.volume24HourTo.toString(),
            openDay = coinDBModel.openDay.toString(),
            highDay = coinDBModel.highDay.toString(),
            lowDay = coinDBModel.lowDay.toString(),
            open24Hour = coinDBModel.open24Hour.toString(),
            high24Hour = coinDBModel.high24Hour.toString(),
            low24Hour = coinDBModel.low24Hour.toString(),
            lastMarket = coinDBModel.lastMarket.toString(),
            volumeHour = coinDBModel.volumeHour.toString(),
            volumeHourTo = coinDBModel.volumeHourTo.toString(),
            openHour = coinDBModel.openHour.toString(),
            highHour = coinDBModel.highHour.toString(),
            lowHour = coinDBModel.lowHour.toString(),
            topTierVolume24Hour = coinDBModel.topTierVolume24Hour.toString(),
            topTierVolume24HourTo = coinDBModel.totalTopTierVolume24HourTo.toString(),
            change24Hour = coinDBModel.change24Hour.toString(),
            changePCT24Hour = coinDBModel.changePCT24Hour.toString(),
            changeDay = coinDBModel.changeDay.toString(),
            changePCTDay = coinDBModel.changePCTDay.toString(),
            supply = coinDBModel.supply.toString(),
            mktCap = coinDBModel.mktCap.toString(),
            totalVolume24Hour = coinDBModel.totalVolume24Hour.toString(),
            totalVolume24HourTo = coinDBModel.totalVolume24HourTo.toString(),
            totalTopTierVolume24Hour = coinDBModel.totalTopTierVolume24Hour.toString(),
            totalTopTierVolume24HourTo = coinDBModel.totalTopTierVolume24HourTo.toString(),
            imageUrl = RetrofitApiFactory.BASE_IMAGE_URL +
                coinDBModel.imageUrl.toString() ,
            fromSymbol = coinDBModel.fromSymbol.toString(),
            toSymbol = coinDBModel.toSymbol.toString(),

        )
    }

    private    fun convertTimestampToTime(timestamp: Long?): String {
        if (timestamp == null) return ""
        val stamp = Timestamp(timestamp * 1000)
        val date = Date(stamp.time)
        val pattern = "HH:mm:ss"
        val sdf = SimpleDateFormat(pattern, Locale.getDefault())
        sdf.timeZone = TimeZone.getDefault()
        return sdf.format(date)
    }

    suspend fun dbListMapper(list2: List<CoinDBModel>): List<Coin> {
        val list1: MutableList<Coin> = mutableListOf()
        list2.forEach {
            list1.add(coinDBModelToCoin(it))
        }
        return list1
    }



    companion object{

        private var ID = 0
    }
}
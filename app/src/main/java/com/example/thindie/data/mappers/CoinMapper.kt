package com.example.thindie.data.mappers

import com.example.thindie.data.api.CoinApiService
import com.example.thindie.data.api.RetrofitApiFactory
import com.example.thindie.data.api.network.CoinDTO
import com.example.thindie.data.api.network.CoinJsonObjectDTO
import com.example.thindie.data.api.network.CoinNameContainerDTO
import com.example.thindie.data.database.CoinDbModel
import com.example.thindie.domain.Coin
import com.google.gson.Gson
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

class CoinMapper {

     fun coinDTOToCoin(coinDTO: CoinDTO): Coin {

        return Coin(
            market = coinDTO.market.toString(),
            price = coinDTO.price.toString(),
            lastUpdate = coinDTO.lastUpdate.toString(),
            highDay = coinDTO.highDay.toString(),
            lowDay = coinDTO.lowDay.toString(),
            lastMarket = coinDTO.lastMarket.toString(),
            imageUrl = RetrofitApiFactory.BASE_IMAGE_URL +
                    coinDTO.imageUrl.toString(),
            fromSymbol = coinDTO.fromSymbol.toString(),
            toSymbol = coinDTO.toSymbol.toString(),

            )
    }

     fun coinDTOToCoinDBModel(coinDTO: CoinDTO): CoinDbModel {

        return CoinDbModel(
            market = coinDTO.market.toString(),
            price = coinDTO.price.toString(),
            lastUpdate = coinDTO.lastUpdate.toString(),
            highDay = coinDTO.highDay.toString(),
            lowDay = coinDTO.lowDay.toString(),
            lastMarket = coinDTO.lastMarket.toString(),
            imageUrl = RetrofitApiFactory.BASE_IMAGE_URL +
                    coinDTO.imageUrl.toString(),
            fromSymbol = coinDTO.fromSymbol.toString(),
            toSymbol = coinDTO.toSymbol.toString(),

            )
    }

    fun coinDBModeltoCoin(сoinDbModel: CoinDbModel): Coin {

        return Coin(
            market = сoinDbModel.market.toString(),
            price = сoinDbModel.price.toString(),
            lastUpdate = сoinDbModel.lastUpdate.toString(),
            highDay = сoinDbModel.highDay.toString(),
            lowDay = сoinDbModel.lowDay.toString(),
            lastMarket = сoinDbModel.lastMarket.toString(),
            imageUrl = RetrofitApiFactory.BASE_IMAGE_URL +
                    сoinDbModel.imageUrl.toString(),
            fromSymbol = сoinDbModel.fromSymbol.toString(),
            toSymbol = сoinDbModel.toSymbol.toString(),

            )
    }

    fun convertTimestampToTime(timestamp: Long?): String {
        if (timestamp == null) return ""
        val stamp = Timestamp(timestamp * 1000)
        val date = Date(stamp.time)
        val pattern = "HH:mm:ss"
        val sdf = SimpleDateFormat(pattern, Locale.getDefault())
        sdf.timeZone = TimeZone.getDefault()
        return sdf.format(date)
    }

    fun fromJsonToListCoinDTO(
        coinJsonObjectDTO: MutableList<CoinJsonObjectDTO>
    ): MutableList<CoinDTO> {
        val coinDTOList: MutableList<CoinDTO> = mutableListOf()

        coinJsonObjectDTO.forEach {
            val jsonObject = it.json ?: return coinDTOList
            val coinKeySet = jsonObject.keySet()
            for (coinKey in coinKeySet) {
                val currencyJson = jsonObject.getAsJsonObject(coinKey)
                val currencyKeySet = currencyJson.keySet()
                for (currencyKey in currencyKeySet) {
                    var key = currencyKey
                    if (key.equals("imageUrl")) {
                        key = key.plus(RetrofitApiFactory.BASE_IMAGE_URL)
                    }
                    val priceInfo = Gson().fromJson(
                        currencyJson.getAsJsonObject(key),
                        CoinDTO::class.java
                    )
                    coinDTOList.add(priceInfo)
                }
            }
        }

        return coinDTOList
    }

    suspend fun getNameCoinNameContainersDTO(coinApiService: CoinApiService)
            : List<CoinNameContainerDTO> {
        val coinNameContainerDTOList = mutableListOf<CoinNameContainerDTO>()
        coinApiService.getTopCoinsInfo(limit = 10)
            .data
            ?.map {
                it.coinNameContainerDTO
            }?.map {
                coinNameContainerDTOList.add(it!!)
            }
        return coinNameContainerDTOList
    }

    suspend fun fromCoinNameContainersToJson(
        coinNameContainerDTOList: List<CoinNameContainerDTO>,
        coinApiService: CoinApiService
    ): MutableList<CoinJsonObjectDTO> {
        val coinJsonObjectDTO: MutableList<CoinJsonObjectDTO> = mutableListOf()
        coinNameContainerDTOList.map {
            coinJsonObjectDTO.add(coinApiService.getFullPriceList(fSyms = it.name!!))

        }
        return coinJsonObjectDTO
    }

}
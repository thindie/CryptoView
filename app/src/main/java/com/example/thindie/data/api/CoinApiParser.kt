package com.example.thindie.data.api

import android.util.Log
import com.example.thindie.data.CoinDBModel
import com.google.gson.Gson
import kotlinx.coroutines.delay

class CoinApiParser(coinApiService: CoinApiService) {
    private var count = 0
    private val retrofit = coinApiService
    private val coinDbModelList : MutableList<CoinDBModel> = mutableListOf()

    suspend fun parse(): List<CoinDBModel> {
        getCoinPriceRawData()

        return  coinDbModelList.toList()
    }

    private suspend fun getCoinPriceRawData()   {

        retrofit.getTopCoinsInfo(limit = 10).body()
            ?.data
            ?.map {
                it.coinInfoToName
            }
            ?.forEach {
                it!!.name
                val raw = retrofit.getFullPriceList(fSyms = it.name!!).body()!!
                synchronized(Any()){

                }
                getPriceListFromRawData(raw)

            }
    }

    private  fun getPriceListFromRawData(
        coinPriceInfoRawData: CoinPriceInfoRawData
    )  {

        val jsonObject = coinPriceInfoRawData.coinPriceInfoJsonObject ?: return
        val coinKeySet = jsonObject.keySet()
        for (coinKey in coinKeySet) {
            val currencyJson = jsonObject.getAsJsonObject(coinKey)
            val currencyKeySet = currencyJson.keySet()
            for (currencyKey in currencyKeySet) {
                var key = currencyKey
                if(key.equals("imageUrl")){
                    key = key.plus(RetrofitApiFactory.BASE_IMAGE_URL)
                }
                val priceInfo = Gson().fromJson(
                    currencyJson.getAsJsonObject(key),
                    CoinDBModel::class.java
                )
                synchronized(Any()){

                    coinDbModelList.add(priceInfo)
                }

            }
        }

    }


}
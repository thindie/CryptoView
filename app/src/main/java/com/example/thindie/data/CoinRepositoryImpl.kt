package com.example.thindie.data

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.thindie.data.api.CoinPriceInfoRawData
import com.example.thindie.data.api.RetrofitApiFactory
import com.example.thindie.domain.Coin
import com.example.thindie.domain.CoinRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient

class CoinRepositoryImpl(
    application: Application
) : CoinRepository {

    private val dbCoin = AppDataBase.getInstance(application)
    private val retrofit = RetrofitApiFactory.apiService
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private val coinListLiveData = MutableLiveData<List<Coin>>()
    private val coinList: MutableList<Coin> = mutableListOf()


    override fun getCoin(id: Int): Coin {
        return coinList[id]
    }

    override fun getList(): LiveData<List<Coin>> {


        coroutineScope.launch {
            val rawData = retrofit.getTopCoinsInfo(
                limit = 50
            ).body()
                ?.data
                ?.map {
                    it.coinInfoToName?.name
                }
                ?.joinToString { "," }

            val rawDataTwo = retrofit.getFullPriceList(fSyms = rawData.toString()).body()
            rawDataTwo?.let { getPriceListFromRawData(rawDataTwo) }

            //Log.d("SERVICE_TAG", "$rawData ")
        }









        coinListLiveData.value = coinList.apply {
            add(0, Coin(0))
            add(1, Coin(1))
        }
        return coinListLiveData
    }

    private fun getPriceListFromRawData(
        coinPriceInfoRawData: CoinPriceInfoRawData
    ): List<CoinDBModel> {
        val result = ArrayList<CoinDBModel>()
        val jsonObject = coinPriceInfoRawData.coinPriceInfoJsonObject ?: return result
        val coinKeySet = jsonObject.keySet()
        for (coinKey in coinKeySet) {
            val currencyJson = jsonObject.getAsJsonObject(coinKey)
            val currencyKeySet = currencyJson.keySet()
            for (currencyKey in currencyKeySet) {
                val priceInfo = Gson().fromJson(
                    currencyJson.getAsJsonObject(currencyKey),
                    CoinDBModel::class.java
                )
                result.add(priceInfo)
            }
        }
        return result
    }

}



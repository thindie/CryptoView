package com.example.thindie.data

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.thindie.data.api.CoinApiParser
import com.example.thindie.data.api.CoinPriceInfoRawData
import com.example.thindie.data.api.RetrofitApiFactory
import com.example.thindie.domain.Coin
import com.example.thindie.domain.CoinRepository
import com.google.gson.Gson
import kotlinx.coroutines.*

class CoinRepositoryImpl(
    application: Application
) : CoinRepository {

    private val dbCoin = AppDataBase.getInstance(application)
    private val retrofit = RetrofitApiFactory.apiService
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private val coinListLiveData = MutableLiveData<List<Coin>>()
    private val coinList: MutableList<Coin> = mutableListOf()
    private var coinDbModelList : MutableList<CoinDBModel> = mutableListOf()


    override fun getCoin(id: Int): Coin {
        return coinList[id]
    }


    override fun getList(): LiveData<List<Coin>> {
         observeList()
        return coinListLiveData
    }

   private fun observeList(){
    val parser = CoinApiParser(retrofit)
    coroutineScope.launch {
        coinDbModelList.addAll(parser.parse())
    }

        coroutineScope.launch {

            Log.d("SERVICE_TAG", "SECOND_SCOPE")
            val mapper = CoinMapper()
            coinList.addAll(mapper.dbListMapper(coinDbModelList))
            coinListLiveData.postValue(coinList)
        }

}

}




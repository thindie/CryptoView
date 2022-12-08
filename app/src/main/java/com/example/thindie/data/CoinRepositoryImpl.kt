package com.example.thindie.data

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.thindie.data.api.CoinApiParser
import com.example.thindie.data.api.RetrofitApiFactory
import com.example.thindie.domain.Coin
import com.example.thindie.domain.CoinRepository
import kotlinx.coroutines.*

class CoinRepositoryImpl(
    application: Application
) : CoinRepository {

    private val dbCoin = AppDataBase.getInstance(application)
    private val retrofit = RetrofitApiFactory.apiService
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private val coinListLiveData = MutableLiveData<List<Coin>>()
    private val coinList: MutableList<Coin> = mutableListOf()
    private var coinDbModelList: MutableList<CoinDBModel> = mutableListOf()


    override fun getCoin(id: Int): Coin {
        return coinList[id]
    }


    override fun getList(): LiveData<List<Coin>> {

        val parser = CoinApiParser(retrofit)
        val mapper = CoinMapper()
        val listDBModel = runBlocking {
            parser.parse()
        }
        coinDbModelList.addAll(listDBModel)
        runBlocking {
            val listCoinDbModel = mapper.dbListMapper(coinDbModelList)
            coinList.addAll(listCoinDbModel)
        }
        coinListLiveData.value = (coinList)
        return coinListLiveData
    }

}




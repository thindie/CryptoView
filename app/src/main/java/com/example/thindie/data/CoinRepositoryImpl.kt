package com.example.thindie.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.thindie.data.api.RetrofitApiFactory
import com.example.thindie.domain.Coin
import com.example.thindie.domain.CoinRepository

class CoinRepositoryImpl(
application: Application
)    : CoinRepository {

    private val dbCoin = AppDataBase.getInstance(application)
    private val retrofit = RetrofitApiFactory.apiService

    private val coinListLiveData = MutableLiveData<List<Coin>>()
    private val coinList: MutableList<Coin> = mutableListOf()


    override fun getCoin(id: Int): Coin {
        return coinList[id]
    }

    override fun getList(): LiveData<List<Coin>> {



        coinListLiveData.value = coinList.apply {
            add(0,Coin(0))
            add(1,Coin(1))
        }
        return coinListLiveData
    }

}



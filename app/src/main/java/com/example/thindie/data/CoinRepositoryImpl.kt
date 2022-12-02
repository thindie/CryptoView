package com.example.thindie.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.thindie.data.CoinRepositoryImpl.Companion.Companion.CURRENT_IMPLEMENTATION_ZERO
import com.example.thindie.domain.Coin
import com.example.thindie.domain.CoinRepository

object CoinRepositoryImpl : CoinRepository {
    private val coinListLiveData = MutableLiveData<List<Coin>>()
    private val coinList: MutableList<Coin> = mutableListOf()
    private var idIncrement = CURRENT_IMPLEMENTATION_ZERO

    override fun getCoin(id: Int): Coin {
        return coinList[id]
    }

    override fun getList(): LiveData<List<Coin>> {
        for (quota in 1 until 10) {
            coinList.add(Coin(idIncrement++))
        }
        coinListLiveData.value = coinList
        return coinListLiveData
    }

    class Companion {
        companion object {
            const val CURRENT_IMPLEMENTATION_ZERO = 0
        }
    }
}



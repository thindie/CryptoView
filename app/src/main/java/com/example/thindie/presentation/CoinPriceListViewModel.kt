package com.example.thindie.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.thindie.data.CoinRepositoryImpl
import com.example.thindie.domain.Coin
import com.example.thindie.domain.GetCoinUseCase
import com.example.thindie.domain.GetCoinsListUseCase

class CoinPriceListViewModel : ViewModel() {
    private val coinRepository = CoinRepositoryImpl
    private val getCoinUseCase = GetCoinUseCase(coinRepository)
    private val getCoinsListUseCase = GetCoinsListUseCase(coinRepository)

    private val _coinList = MutableLiveData<List<Coin>>()
    val coinList: LiveData<List<Coin>>
        get() = _coinList

    private val _coin = MutableLiveData<Coin>()
    val coin: LiveData<Coin>
        get() = _coin


    fun getCoinList() {
        _coinList.value = getCoinsListUseCase.getList().value
    }

    fun getCoin(id: Int) {
        _coin.value = getCoinUseCase.getCoin(id)
    }

}
package com.example.thindie.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.thindie.data.CoinRepositoryImpl
import com.example.thindie.domain.Coin
import com.example.thindie.domain.GetCoinUseCase
import com.example.thindie.domain.GetCoinsListUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CoinPriceListViewModel(application: Application) : AndroidViewModel(application) {
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    private val coinRepository = CoinRepositoryImpl(application)
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
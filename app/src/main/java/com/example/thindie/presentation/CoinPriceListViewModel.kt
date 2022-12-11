package com.example.thindie.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.thindie.data.repository.CoinRepositoryImpl
import com.example.thindie.domain.Coin
import com.example.thindie.domain.GetCoinUseCase
import com.example.thindie.domain.GetCoinsListUseCase
import com.example.thindie.domain.LoadDataUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CoinPriceListViewModel(application: Application) : AndroidViewModel(application) {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private val coinRepository = CoinRepositoryImpl(application)
    private val getCoinUseCase = GetCoinUseCase(coinRepository)
    private val getCoinsListUseCase = GetCoinsListUseCase(coinRepository)
    private val loadData = LoadDataUseCase(coinRepository)

    private val _coinList = MutableLiveData<List<Coin>>()
    val coinList: LiveData<List<Coin>>
        get() = _coinList

    private val _coin = MutableLiveData<Coin>()
    val coin: LiveData<Coin>
        get() = _coin


    fun loadData() {
        coroutineScope.launch {
            loadData.loadData()
        }
    }

    fun getCoinList() {
        val list = getCoinsListUseCase.getList().value!!
        coroutineScope.launch {

            _coinList.postValue(
                list
            )

        }


    }

    fun getCoin(fromSymbol: String) {
        coroutineScope.launch {
            getCoinUseCase.getCoin(fromSymbol)
            _coin.value = getCoinUseCase.getCoin(fromSymbol).value
        }
        //
    }

}
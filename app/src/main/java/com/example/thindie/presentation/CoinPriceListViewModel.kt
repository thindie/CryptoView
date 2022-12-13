package com.example.thindie.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.thindie.data.repository.CoinRepositoryImpl
import com.example.thindie.domain.Coin
import com.example.thindie.domain.GetCoinUseCase
import com.example.thindie.domain.GetCoinsListUseCase
import com.example.thindie.domain.LoadDataUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CoinPriceListViewModel(application: Application) : AndroidViewModel(application) {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private val coinRepository = CoinRepositoryImpl(application)
    private val getCoinUseCase = GetCoinUseCase(coinRepository)
    private val getCoinsListUseCase = GetCoinsListUseCase(coinRepository)
    private val loadData = LoadDataUseCase(coinRepository)

    val coinInfoList = getCoinsListUseCase.getList()

    fun detailInfo(fromSymbol: String) = getCoinUseCase.getCoin(fromSymbol)

    init {
        viewModelScope.launch {
                loadData.loadData()
        }
    }

}


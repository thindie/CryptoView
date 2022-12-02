package com.example.thindie.domain

import androidx.lifecycle.LiveData

class GetCoinsListUseCase(private val coinRepository: CoinRepository)  {
    fun getList() : LiveData<List<Coin>> {
        return coinRepository.getList()
    }
}
package com.example.thindie.domain

import androidx.lifecycle.LiveData

interface CoinRepository {
    fun getCoin(fromSymbol : String) : LiveData<Coin>
    fun getList(): LiveData<List<Coin>>
    suspend fun loadData()
}
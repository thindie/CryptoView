package com.example.thindie.domain

import androidx.lifecycle.LiveData

interface CoinRepository {
    fun getCoin(id: Int) : Coin
    fun getList(): LiveData<List<Coin>>
}
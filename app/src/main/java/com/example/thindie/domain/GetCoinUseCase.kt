package com.example.thindie.domain

import androidx.lifecycle.LiveData

class GetCoinUseCase(private val coinRepository: CoinRepository) {
    fun getCoin(fromSymbol : String) : LiveData<Coin>{
        return coinRepository.getCoin(fromSymbol)
    }
}
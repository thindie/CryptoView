package com.example.thindie.domain

class GetCoinUseCase(private val coinRepository: CoinRepository) {
    fun getCoin(id : Int) : Coin{
        return coinRepository.getCoin(id)
    }
}
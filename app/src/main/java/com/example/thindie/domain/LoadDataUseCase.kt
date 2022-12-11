package com.example.thindie.domain

class LoadDataUseCase (private val coinRepository: CoinRepository) {

   suspend fun loadData() {
        coinRepository.loadData()
    }
}
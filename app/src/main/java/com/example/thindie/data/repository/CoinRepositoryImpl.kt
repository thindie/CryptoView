package com.example.thindie.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.thindie.data.api.RetrofitApiFactory
import com.example.thindie.data.database.AppDataBase
import com.example.thindie.data.database.CoinDbModel
import com.example.thindie.data.mappers.CoinMapper
import com.example.thindie.domain.Coin
import com.example.thindie.domain.CoinRepository
import kotlinx.coroutines.delay

class CoinRepositoryImpl(
    application: Application
) : CoinRepository {

    private val dbCoin = AppDataBase.getInstance(application).coinListDao()
    private val mapper = CoinMapper()
    private val retrofit = RetrofitApiFactory.apiService


    override fun getCoin(fromSymbol: String): LiveData<Coin> {

        return Transformations.map(dbCoin.getPriceInfoAboutCoin(fromSymbol)) {
            mapper.coinDBModelToCoin(it)
        }
    }

    override fun getList(): LiveData<List<Coin>> {
        return Transformations.map(dbCoin.getPriceList()) {
            it.map {
                mapper.coinDBModelToCoin(it)
            }
        }
    }

    override suspend fun loadData() {
        while (true) {
            try {
                val nameContainers = mapper.getNameCoinNameContainersDTO(retrofit)
                val coinJsonObj = mapper.fromCoinNameContainersToJson(nameContainers, retrofit)
                val listCoinDto = mapper.fromJsonToListCoinDTO(coinJsonObj)
                val dbModelList = listCoinDto.map {
                    mapper.coinDTOToCoinDBModel(it)
                }
                dbCoin.insertPriceList(dbModelList)
            } catch (e: Exception) {
            }
            delay(10000)
        }

    }

}




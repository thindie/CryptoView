package com.example.thindie.data

import com.example.thindie.domain.Coin

class CoinMapper {
    fun coinDBModelToCoin(coinDBModel: CoinDBModel): Coin {
        return Coin(
            price = coinDBModel.price!!,
            priceLevel = coinDBModel.high24Hour!!,
            maxPrice = coinDBModel.high24Hour,
            minPrice = coinDBModel.low24Hour!!,
            maxPriceLabel = "",
            minPriceLabel = "",
            fromSymbol = coinDBModel.fromSymbol,
            toSymbol = coinDBModel.toSymbol!!,
            lastMarket = coinDBModel.lastMarket!!,
            lastMarketLabel = "",
            lastUpdate = coinDBModel.lastUpdate!!.toString(),
            lastUpdateLabel = "",
            image = coinDBModel.imageUrl.toString(),
        )
    }

    fun dbListMapper(list2: List<CoinDBModel>): List<Coin> {
        val list1: MutableList<Coin> = mutableListOf()
        list2.forEach {
            list1.add(coinDBModelToCoin(it))
        }
        return list1
    }
}
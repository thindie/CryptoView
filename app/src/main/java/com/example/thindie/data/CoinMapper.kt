package com.example.thindie.data

import com.example.thindie.domain.Coin

class CoinMapper {
    fun coinDBModelToCoin(coinDBModel: CoinDBModel) : Coin{
        return Coin(
            name  = coinDBModel.type!!,
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
}
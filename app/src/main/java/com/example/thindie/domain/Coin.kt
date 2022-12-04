package com.example.thindie.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Coin(
    val id: Int,
    var name: String = WILL_BE_SET_LATER,
    var price: String = WILL_BE_SET_LATER,
    val slash: String = SLASH,
    var lastUpdate : String = WILL_BE_SET_LATER,
    var lastMarket : String = WILL_BE_SET_LATER,
    var fromSymbol: String = WILL_BE_SET_LATER,
    var lastMarketLabel: String = WILL_BE_SET_LATER,
    var lastUpdateLabel: String = WILL_BE_SET_LATER,
    var maxPrice: String = WILL_BE_SET_LATER,
    var maxPriceLabel: String = WILL_BE_SET_LATER,
    var minPrice: String = WILL_BE_SET_LATER,
    var minPriceLabel: String = WILL_BE_SET_LATER,
    var image: String = WILL_BE_SET_LATER,
    var priceLevel: String = WILL_BE_SET_LATER,
    var toSymbol: String = WILL_BE_SET_LATER

) : Parcelable {
    companion object {
        private const val SLASH = " / "
        private const val WILL_BE_SET_LATER = " YOU ADS HERE LATER "
    }
}

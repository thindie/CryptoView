package com.example.thindie.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Coin(
    val id: Int,
    var name: String = WILL_BE_SET_LATER,
    var rate: String = WILL_BE_SET_LATER,
    var minToday: String = WILL_BE_SET_LATER,
    var maxToday: String = WILL_BE_SET_LATER,
    var lastUpdateTime: String = WILL_BE_SET_LATER,
    var image: String = WILL_BE_SET_LATER

) : Parcelable {
    companion object {
        private const val WILL_BE_SET_LATER = " "
    }
}

package com.example.thindie.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coins_table")
data class CoinDbModel(
    val market: String?,
    @PrimaryKey
    val fromSymbol: String,
    val toSymbol: String?,
    val price: String?,
    val lastUpdate: String?,
    val highDay: String?,
    val lowDay: String?,
    val lastMarket: String?,
    val imageUrl: String?,
)
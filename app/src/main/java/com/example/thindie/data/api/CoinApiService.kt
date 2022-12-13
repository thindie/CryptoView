package com.example.thindie.data.api

import com.example.thindie.data.api.network.CoinInfoListOfDataDTO
import com.example.thindie.data.api.network.CoinJsonObjectDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinApiService {

    @GET("top/totalvolfull")
   suspend fun getTopCoinsInfo(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = "",
        @Query(QUERY_PARAM_LIMIT) limit: Int ,
        @Query(QUERY_PARAM_TO_SYMBOL) tSym: String = CURRENCY

    ): CoinInfoListOfDataDTO

    @GET("pricemultifull")
   suspend fun getFullPriceList(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = "",
        @Query(QUERY_PARAM_FROM_SYMBOLS) fSyms: String,
        @Query(QUERY_PARAM_TO_SYMBOLS) tSyms: String = CURRENCY
    ): CoinJsonObjectDTO

    companion object {
        private const val QUERY_PARAM_API_KEY = "api_key"
        private const val QUERY_PARAM_LIMIT = "limit"
        private const val QUERY_PARAM_TO_SYMBOL = "tsym"
        private const val QUERY_PARAM_TO_SYMBOLS = "tsyms"
        private const val QUERY_PARAM_FROM_SYMBOLS = "fsyms"
        private const val CURRENCY = "USD"
    }
}

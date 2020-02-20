package com.firstproject.lisabg_oblig2.model

import retrofit2.http.GET
import retrofit2.Response

interface AlpacaApi {

    @GET("alpakka0.json")
    suspend fun fetch0Alpacas(): Response<List<Alpaca>>

    @GET("alpakka2.json")
    suspend fun fetch2Alpacas(): Response<List<Alpaca>>

    @GET("alpakka20.json")
    suspend fun fetch20Alpacas(): Response<List<Alpaca>>

    @GET("alpakka200.json")
    suspend fun fetch200Alpacas(): Response<List<Alpaca>>

    @GET("alpakka2000.json")
    suspend fun fetch2000Alpacas(): Response<List<Alpaca>>

}
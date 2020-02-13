package com.firstproject.lisabg_oblig2

import retrofit2.Call
import retrofit2.http.GET

interface AlpacaApi {

    @GET("alpakka0.json")
    suspend fun fetch0Alpacas(): List<Alpaca>

    @GET("alpakka2.json")
    suspend fun fetch2Alpacas(): List<Alpaca>

    @GET("alpakka20.json")
    suspend fun fetch20Alpacas(): List<Alpaca>

    @GET("alpakka200.json")
    suspend fun fetch200Alpacas(): List<Alpaca>

    @GET("alpakka2000.json")
    suspend fun fetch2000Alpacas(): List<Alpaca>

}
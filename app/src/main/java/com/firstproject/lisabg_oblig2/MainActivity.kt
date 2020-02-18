package com.firstproject.lisabg_oblig2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    private lateinit var apiResult : Deferred<List<Alpaca>>
    private lateinit var job1 : Job
    private val alpacaHttp =
        "https://www.uio.no/studier/emner/matnat/ifi/IN2000/v20/obligatoriske-oppgaver/"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_textview1.text = "first"

        job1 = GlobalScope.launch{
            apiResult = async { retrofit().fetch2Alpacas() }

            val alpacas = apiResult.await()
            main_textview2.text = alpacas.toString()
        }


        main_textview3.text = "last"

    }




    private fun retrofit(): AlpacaApi {
        return Retrofit.Builder()
            .baseUrl(alpacaHttp)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(AlpacaApi::class.java)
    }

}

package com.firstproject.lisabg_oblig2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    val alpacas = MutableLiveData<List<Alpaca>>()
    private val alpacaHttp =
        "https://www.uio.no/studier/emner/matnat/ifi/IN2000/v20/obligatoriske-oppgaver/"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        GlobalScope.launch{
            val alpacas = retrofit().fetch2Alpacas()
            main_textview.text = alpacas.toString()
        }


    }




    private fun retrofit(): AlpacaApi {
        return Retrofit.Builder()
            .baseUrl(alpacaHttp)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(AlpacaApi::class.java)
    }

}

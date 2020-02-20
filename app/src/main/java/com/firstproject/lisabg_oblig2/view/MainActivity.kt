package com.firstproject.lisabg_oblig2.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.firstproject.lisabg_oblig2.R
import com.firstproject.lisabg_oblig2.model.Alpaca
import com.firstproject.lisabg_oblig2.model.AlpacaApi
import com.firstproject.lisabg_oblig2.viewmodel.ListViewModel
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    lateinit var viewModel: ListViewModel
    private val alpacasAdapter = AlpacaListAdapter(arrayListOf())

    private lateinit var apiResult : Deferred<List<Alpaca>>
    private lateinit var job1 : Job


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.refresh()

        alpaca_list.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = alpacasAdapter
        }

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.alpacas.observe(this, Observer {alpacas ->
            alpacas?.let {
                alpaca_list.visibility = View.VISIBLE
                alpacasAdapter.updateAlpacas(it) }
        })

        viewModel.alpacaLoadError.observe(this, Observer { isError ->
            list_error.visibility = if(isError == "") View.GONE else View.VISIBLE
        })

        viewModel.loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                loading_view.visibility = if(it) View.VISIBLE else View.GONE
                if(it) {
                    list_error.visibility = View.GONE
                    alpaca_list.visibility = View.GONE
                }
            }
        })
    }
}

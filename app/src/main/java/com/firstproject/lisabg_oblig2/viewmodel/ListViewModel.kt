package com.firstproject.lisabg_oblig2.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.firstproject.lisabg_oblig2.model.Alpaca
import com.firstproject.lisabg_oblig2.model.AlpacaService
import kotlinx.coroutines.*
import kotlin.coroutines.coroutineContext

class ListViewModel : ViewModel() {

    val alpacaService = AlpacaService.getAlpacaService()
    var job: Job? = null
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        onError("Exception: ${throwable.localizedMessage}")

    }

    val alpacas = MutableLiveData<List<Alpaca>>()
    val alpacaLoadError = MutableLiveData<String?>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {
        fetchAlpacas()
    }

    private fun fetchAlpacas() {
        loading.value = true

        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = alpacaService.fetch0Alpacas()

            delay(4000L) // for å se progressbar

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    alpacas.value = response.body()
                    alpacaLoadError.value = ""
                    loading.value = false
                } else {
                    onError("Error: ${response.message()}")
                }
            }
        }
    }

    private fun onError(message: String) {
        alpacaLoadError.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}
package com.start.filmsapp

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.start.filmsapp.model.Film
import com.start.filmsapp.model.FilmResponse
import com.start.filmsapp.services.FilmApiInterface
import com.start.filmsapp.services.FilmApiService
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()
    lateinit var liveDataList: MutableLiveData<List<Film>>


    init {
        liveDataList = MutableLiveData()
        viewModelScope.launch {
            delay(2000)
            _isLoading.value = false
        }
    }

    fun getLiveDataObserver(): MutableLiveData<List<Film>> {
        return liveDataList
    }

    fun makeAPICall() {
        val retroInstance = FilmApiService.getInstance()
        val retroService = retroInstance.create(FilmApiInterface::class.java)
        val call = retroService.getFilmList()
        call.enqueue(object: Callback<FilmResponse> {
            override fun onResponse(call: Call<FilmResponse>, response: Response<FilmResponse>) {
                liveDataList.postValue(response.body()?.films)
            }

            override fun onFailure(call: Call<FilmResponse>, t: Throwable) {

            }
        })
    }

}

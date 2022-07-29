package com.start.filmsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import com.start.filmsapp.model.Film
import com.start.filmsapp.model.FilmResponse
import com.start.filmsapp.services.FilmApiInterface
import com.start.filmsapp.services.FilmApiService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition{
                viewModel.isLoading.value}
        }
        setContentView(R.layout.activity_main)

        rv_films_list.layoutManager = LinearLayoutManager(this)
        rv_films_list.setHasFixedSize(true)
        getFilmData { films: List<Film> ->
            rv_films_list.adapter = FilmAdapter(films)
        }
    }

    private fun getFilmData(callback: (List<Film>) -> Unit){
        val apiService = FilmApiService.getInstance().create(FilmApiInterface::class.java)
        apiService.getFilmList().enqueue(object: Callback<FilmResponse> {
            override fun onResponse(call: Call<FilmResponse>, response: Response<FilmResponse>) {
                return callback(response.body()!!.films)
            }

            override fun onFailure(call: Call<FilmResponse>, t: Throwable) {

            }

        })
    }
}
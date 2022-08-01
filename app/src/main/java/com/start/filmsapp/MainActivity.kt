package com.start.filmsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.start.filmsapp.databinding.ActivityMainBinding
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
    private lateinit var recyclerAdapter: FilmAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition{
                viewModel.isLoading.value}
        }
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initRecyclerView()
        initViewModel()
    }

    private fun initRecyclerView() {
        binding.rvFilmsList.layoutManager = LinearLayoutManager(this)
        recyclerAdapter = FilmAdapter(this)
        binding.rvFilmsList.adapter = recyclerAdapter

    }

    private fun initViewModel() {
        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getLiveDataObserver().observe(this) {
            if (it != null) {
                recyclerAdapter.setFilmList(it)
                recyclerAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(this, "Error in getting list", Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.makeAPICall()
    }
}

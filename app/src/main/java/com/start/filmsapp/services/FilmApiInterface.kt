package com.start.filmsapp.services

import com.start.filmsapp.model.FilmResponse
import retrofit2.Call
import retrofit2.http.GET

interface FilmApiInterface {

    @GET("/svc/movies/v2/reviews/search.json?api-key=PgZdZUfhxAGgggEEjGwHFuXaAzGLZozl")
    fun getFilmList(): Call<FilmResponse>
}
// https://api.nytimes.com/svc/movies/v2/reviews/search.json?api-key=PgZdZUfhxAGgggEEjGwHFuXaAzGLZozl
package com.start.filmsapp

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.start.filmsapp.model.Film
import kotlinx.android.synthetic.main.film_item.view.*

class FilmAdapter(val activity: Activity): RecyclerView.Adapter<FilmAdapter.FilmViewHolder>() {


    private var films: List<Film>? = null

    fun setFilmList(films: List<Film>?) {
        this.films = films
    }

    class FilmViewHolder(view: View): RecyclerView.ViewHolder(view){
        fun bindFilm(film: Film){
            itemView.film_title.text = film.title
            itemView.film_description.text = film.description
            Glide.with(itemView).load(film.poster?.src).into(itemView.film_poster)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        return FilmViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.film_item,parent,false)
        )
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        holder.bindFilm(films!!.get(position))
    }

    override fun getItemCount(): Int {
        return if (films != null)
            films!!.size
        else 0
    }
}
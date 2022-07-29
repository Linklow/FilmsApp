package com.start.filmsapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FilmResponse(
    @SerializedName("results")
    val films: List<Film>
):
    Parcelable {
    constructor(): this(mutableListOf())
}
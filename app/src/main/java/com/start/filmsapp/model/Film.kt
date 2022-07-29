package com.start.filmsapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Film (

    @SerializedName("display_title")
    val title: String?,


    @SerializedName("multimedia")
    var poster: Poster?,

    @SerializedName("summary_short")
    val description: String?

): Parcelable{
    constructor() : this("",null,"")
}

@Parcelize
data class Poster (
    @SerializedName("src")
    val src: String?,
        ) : Parcelable{
    constructor() : this("")
}
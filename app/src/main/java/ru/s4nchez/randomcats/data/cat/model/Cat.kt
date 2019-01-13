package ru.s4nchez.randomcats.data.cat.model

import com.google.gson.annotations.SerializedName

data class Cat(
    @SerializedName("file")
    val url: String
)
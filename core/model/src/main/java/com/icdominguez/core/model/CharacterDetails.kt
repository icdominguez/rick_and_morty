package com.icdominguez.core.model

data class CharacterDetails(
    val episodes: List<Int>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: String,
    val name: String,
    val origin: String,
    val species: String,
    val status: String,
    val type: String,
)

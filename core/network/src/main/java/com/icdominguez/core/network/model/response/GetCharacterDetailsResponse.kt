package com.icdominguez.core.network.model.response

import com.google.gson.annotations.SerializedName
import com.icdominguez.shared.core.models.characters.Location
import com.icdominguez.shared.core.models.characters.Origin
import kotlinx.serialization.Serializable

@Serializable
data class GetCharacterDetailsResponse(
    @SerializedName("created")
    val created: String,
    @SerializedName("episode")
    val episode: List<String>,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("location")
    val location: Location,
    @SerializedName("name")
    val name: String,
    @SerializedName("origin")
    val origin: Origin,
    @SerializedName("species")
    val species: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("url")
    val url: String,
)

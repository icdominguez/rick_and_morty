package com.icdominguez.shared.core.models.characters

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Origin (
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)
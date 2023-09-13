package com.icdominguez.core.network.model.response

import com.google.gson.annotations.SerializedName
import com.icdominguez.core.network.model.GetCharacterResult
import com.icdominguez.core.network.model.GetCharactersInfo

data class GetCharactersResponse(
    @SerializedName("info")
    val info: GetCharactersInfo,
    @SerializedName("results")
    val results: List<GetCharacterResult>,
)
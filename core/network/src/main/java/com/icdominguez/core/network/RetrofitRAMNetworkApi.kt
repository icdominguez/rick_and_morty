package com.icdominguez.core.network

import com.icdominguez.core.network.model.response.GetCharacterDetailsResponse
import com.icdominguez.core.network.model.response.GetCharactersResponse
import com.icdominguez.core.network.model.response.GetEpisodeResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitRAMNetworkApi {

    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: Int,
        @Query("name") name: String? = null,
        @Query("status") status: String? = null,
        @Query("gender") gender: String? = null,
    ): GetCharactersResponse

    @GET("character/{character_id}")
    suspend fun getCharacterDetails(
        @Path("character_id") characterId: Int,
    ): GetCharacterDetailsResponse

    @GET("episode/{episode}")
    suspend fun getEpisode(
        @Path("episode") episode: Int
    ): GetEpisodeResponse
}

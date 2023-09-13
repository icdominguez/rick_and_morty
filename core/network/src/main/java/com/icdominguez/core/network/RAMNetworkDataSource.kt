package com.icdominguez.core.network

import com.icdominguez.core.network.model.response.GetCharacterDetailsResponse
import com.icdominguez.core.network.model.response.GetCharactersResponse
import com.icdominguez.core.network.model.response.GetEpisodeResponse

interface RAMNetworkDataSource {
    suspend fun getCharacters(page: Int, name: String? = null, status: String? = null, gender: String? = null): GetCharactersResponse
    suspend fun getCharacterDetails(characterId: Int): GetCharacterDetailsResponse

    suspend fun getEpisode(episodeId: Int): GetEpisodeResponse
}

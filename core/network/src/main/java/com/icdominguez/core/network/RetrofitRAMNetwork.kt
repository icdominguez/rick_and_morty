package com.icdominguez.core.network

import com.icdominguez.core.network.model.response.GetCharacterDetailsResponse
import com.icdominguez.core.network.model.response.GetCharactersResponse
import com.icdominguez.core.network.model.response.GetEpisodeResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitRAMNetwork @Inject constructor(
    private val networkApi: RetrofitRAMNetworkApi,
) : RAMNetworkDataSource {
    override suspend fun getCharacters(page: Int, name: String?, status: String?, gender: String?): GetCharactersResponse =
        networkApi.getCharacters(page, name, status, gender)

    override suspend fun getCharacterDetails(characterId: Int): GetCharacterDetailsResponse =
        networkApi.getCharacterDetails(characterId)

    override suspend fun getEpisode(episodeId: Int): GetEpisodeResponse =
        networkApi.getEpisode(episodeId)
}

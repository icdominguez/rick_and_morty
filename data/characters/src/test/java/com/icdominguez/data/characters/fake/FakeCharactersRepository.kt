package com.icdominguez.data.characters.fake

import android.content.res.Resources.NotFoundException
import com.icdominguez.core.network.RAMNetworkDataSource
import com.icdominguez.core.network.model.GetCharacterResult
import com.icdominguez.core.network.model.response.GetCharacterDetailsResponse
import com.icdominguez.core.network.model.response.GetCharactersResponse
import com.icdominguez.core.network.model.response.GetEpisodeResponse

class FakeCharactersRepository : RAMNetworkDataSource {

    private val charactersPages: MutableMap<Int, GetCharactersResponse> = mutableMapOf()
    private var allCharacters: List<GetCharacterResult>

    init {
        charactersPages[1] = FakeData.page1Characters
        charactersPages[2] = FakeData.page2Characters
        charactersPages[3] = FakeData.page3Characters

        allCharacters = buildList {
            FakeData.page1Characters
            FakeData.page2Characters
            FakeData.page3Characters
        }
    }

    override suspend fun getCharacters(page: Int, name: String?, status: String?, gender: String?): GetCharactersResponse {
        return charactersPages.getOrElse(page) {
            throw NotFoundException()
        }
    }

    override suspend fun getCharacterDetails(characterId: Int): GetCharacterDetailsResponse {
        val characterResult = allCharacters.find { character -> character.id == characterId }

        return characterResult?.let {
            it as GetCharacterDetailsResponse
        } ?: throw NotFoundException()
    }

    override suspend fun getEpisode(episodeId: Int): GetEpisodeResponse {
        TODO("Not yet implemented")
    }
}

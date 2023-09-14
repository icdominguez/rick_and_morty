package com.icdominguez.data.characters

import com.icdominguez.core.model.Character
import com.icdominguez.core.model.CharacterDetails
import com.icdominguez.core.network.model.GetCharacterResult
import com.icdominguez.core.network.model.response.GetCharacterDetailsResponse

fun GetCharacterResult.toCharacter(): Character {
    return Character(
        id = id,
        name = name,
        imageUrl = image,
        gender = gender,
        status = status,
        origin = origin.name,
        species = species,
    )
}

fun GetCharacterDetailsResponse.toCharacterDetails(): CharacterDetails {
    return CharacterDetails(
        id = id,
        name = name,
        image = image,
        gender = gender,
        status = status,
        episodes = episode.map { episode ->
            getEpisodeFromUrl(episode) ?: 0
        },
        location = location.name,
        origin = origin.name,
        species = species,
        type = type,
    )
}

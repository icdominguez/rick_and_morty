package com.icdominguez.data.characters.repository

import androidx.paging.PagingData
import com.icdominguez.core.model.Character
import com.icdominguez.core.model.CharacterDetails
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {
    suspend fun getAllCharacters(name: String? = null, status: String? = null, gender: String? = null): Flow<PagingData<Character>>
    suspend fun getCharacterDetails(characterId: Int): Result<CharacterDetails>
}

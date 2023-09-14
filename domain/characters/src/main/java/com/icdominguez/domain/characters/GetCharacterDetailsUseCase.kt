package com.icdominguez.domain.characters

import com.icdominguez.data.characters.repository.CharactersRepository
import javax.inject.Inject

class GetCharacterDetailsUseCase @Inject constructor(
    private val charactersRepository: CharactersRepository,
) {
    suspend operator fun invoke(characterId: Int) = charactersRepository.getCharacterDetails(characterId)
}

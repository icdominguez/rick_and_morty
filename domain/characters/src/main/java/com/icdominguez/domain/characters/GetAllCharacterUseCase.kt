package com.icdominguez.domain.characters

import com.icdominguez.data.characters.repository.CharactersRepository
import javax.inject.Inject

class GetAllCharacterUseCase @Inject constructor(
    private val charactersRepository: CharactersRepository,
) {
    suspend operator fun invoke(name: String? = null, status: String? = null, gender: String? = null) =
        charactersRepository.getAllCharacters(name, status, gender)
}

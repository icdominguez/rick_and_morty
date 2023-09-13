package com.icdominguez.features.characterdetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.icdominguez.core.common.mvi.MviViewModel
import com.icdominguez.core.model.CharacterDetails
import com.icdominguez.core.model.Episode
import com.icdominguez.domain.characters.GetCharacterDetailsUseCase
import com.icdominguez.features.characterdetails.navigation.characterIdArg
import com.icdominguez.rickandmorty.domain.episodes.GetEpisodeByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getCharacterDetailsUseCase: GetCharacterDetailsUseCase,
    private val getEpisodeByIdUseCase: GetEpisodeByIdUseCase,
) : MviViewModel<CharacterDetailsScreenViewModel.State, CharacterDetailsScreenViewModel.Event>() {

    data class State(
        val isLoading: Boolean = false,
        val characterId: Int? = null,
        val data: CharacterDetails? = null,
        val errorMessage: String? = null,
        val episodeList: List<Episode> = emptyList(),
    )

    override var currentState = State()

    private val characterArg: Int = checkNotNull(savedStateHandle[characterIdArg])

    init {
        updateState {
            copy(characterId = characterArg)
        }
        getCharacterDetails()
    }

    private fun getCharacterDetails() {
        viewModelScope.launch {
            state.value.characterId?.let {
                val result = getCharacterDetailsUseCase(it)

                result.onSuccess { details ->
                    updateState {
                        copy(
                            data = details,
                            isLoading = false,
                        )
                    }
                    getEpisodes()
                }

                result.onFailure { error ->
                    updateState {
                        copy(
                            errorMessage = error.message,
                        )
                    }
                }
            }
        }
    }

    private fun getEpisodes() {
        state.value.data?.let { characterDetails ->
            if (characterDetails.episodes.isNotEmpty()) {
                characterDetails.episodes.forEach {
                    viewModelScope.launch {
                        val result = getEpisodeByIdUseCase(it)

                        result.onSuccess { episodeResponse ->
                            updateState {
                                copy(
                                    episodeList = state.value.episodeList.toMutableList().apply {
                                        add(episodeResponse)
                                    },
                                )
                            }
                        }

                        result.onFailure { error ->
                            updateState {
                                copy(
                                    errorMessage = error.message,
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    sealed class Event
}

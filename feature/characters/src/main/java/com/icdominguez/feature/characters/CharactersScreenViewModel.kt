package com.icdominguez.feature.characters

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Female
import androidx.compose.material.icons.filled.Male
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.material.icons.filled.Transgender
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.icdominguez.core.common.mvi.MviViewModel
import com.icdominguez.core.model.Character
import com.icdominguez.domain.characters.GetAllCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersScreenViewModel @Inject constructor(
    private val getAllCharacterUseCase: GetAllCharacterUseCase,
) : MviViewModel<CharactersScreenViewModel.State, CharactersScreenViewModel.Event>() {

    private val _charactersState: MutableStateFlow<PagingData<Character>> =
        MutableStateFlow(value = PagingData.empty())
    val charactersState: MutableStateFlow<PagingData<Character>> get() = _charactersState

    data class State(
        val characterList: PagingData<Character> = PagingData.empty(),
        val showColumn: Boolean = true,
        val searchText: String = "",
        val isSearchBarActive: Boolean = false,
        val searchHistory: List<String> = emptyList(),
        val showBottomSheetDialog: Boolean = false,
        val listFilterStatus: List<FilterStatus> = listOf(
            FilterStatus("alive", true),
            FilterStatus("dead", false),
            FilterStatus("unknown", false),
        ),
        var listFilterGender: List<FilterGender> = listOf(
            FilterGender("Male", Icons.Filled.Male, true),
            FilterGender("Female", Icons.Filled.Female, false),
            FilterGender("Genderless", Icons.Filled.Transgender, false),
            FilterGender("Unknown", Icons.Filled.QuestionMark, false),
        ),
    )

    override var currentState = State()

    init {
        getAllCharacters()
    }

    sealed class Event {
        data object OnChangeViewButtonClicked : Event()
        class OnSearchTextChanged(val newSearchText: String) : Event()
        data object OnSearchClicked : Event()
        data object OnSearchBarActiveChange : Event()
        data object OnSearchBarClose : Event()
        class OnHistoryItemClicked(val historyItemClicked: String) : Event()
        data object OnFilterButtonClicked : Event()
        data object OnBottomSheetDialogDismissed : Event()
        data object OnBottomSheetDialogAccepted : Event()
        class OnFilterStatusSelected(val filterStatus: String) : Event()
        class OnFilterGenderSelected(val filterGender: String) : Event()
        data object OnResetFilterButtonClicked : Event()
    }

    override fun uiEvent(event: Event) {
        when (event) {
            is Event.OnChangeViewButtonClicked -> {
                updateState {
                    copy(
                        showColumn = !state.value.showColumn,
                    )
                }
            }

            is Event.OnSearchTextChanged -> {
                updateState {
                    copy(
                        searchText = event.newSearchText,
                    )
                }
            }

            is Event.OnSearchClicked -> {
                getCharactersByName()
                updateState {
                    copy(
                        isSearchBarActive = false,
                        searchHistory = searchHistory.toMutableList()
                            .apply { this.add(state.value.searchText) },
                    )
                }
            }

            is Event.OnSearchBarActiveChange -> {
                updateState {
                    copy(
                        isSearchBarActive = !isSearchBarActive,
                    )
                }
            }

            is Event.OnSearchBarClose -> {
                getAllCharacters()
                updateState {
                    copy(
                        isSearchBarActive = false,
                        searchText = "",
                    )
                }
            }

            is Event.OnHistoryItemClicked -> {
                updateState {
                    copy(
                        searchText = event.historyItemClicked,
                    )
                }
                getCharactersByName()
                updateState {
                    copy(
                        isSearchBarActive = false,
                        searchText = "",
                    )
                }
            }

            is Event.OnBottomSheetDialogDismissed -> {
                updateState {
                    copy(
                        showBottomSheetDialog = false,
                    )
                }
            }

            is Event.OnBottomSheetDialogAccepted -> {
                updateState {
                    copy(
                        showBottomSheetDialog = false,
                    )
                }
                getAllCharactersFilter()
            }

            is Event.OnFilterButtonClicked -> {
                updateState {
                    copy(
                        showBottomSheetDialog = true,
                    )
                }
            }

            is Event.OnFilterStatusSelected -> {
                val copyList = state.value.listFilterStatus.toMutableList().apply {
                    val selectedItemIndex = indexOfFirst { it.selected }
                    val selectedItem = find { it.selected }

                    val newElementClickedIndex = indexOfFirst { it.name == event.filterStatus }
                    val newSelectedItem = find { it.name == event.filterStatus }

                    this[selectedItemIndex] = selectedItem!!.copy(selected = false)
                    this[newElementClickedIndex] = newSelectedItem!!.copy(selected = true)
                }

                updateState {
                    copy(
                        listFilterStatus = copyList,
                    )
                }
            }

            is Event.OnFilterGenderSelected -> {
                val copyList = state.value.listFilterGender.toMutableList().apply {
                    val selectedItemIndex = indexOfFirst { it.selected }
                    val selectedItem = find { it.selected }

                    val newElementClickedIndex = indexOfFirst { it.name == event.filterGender }
                    val newSelectedItem = find { it.name == event.filterGender }

                    this[selectedItemIndex] = selectedItem!!.copy(selected = false)
                    this[newElementClickedIndex] = newSelectedItem!!.copy(selected = true)
                }

                updateState {
                    copy(
                        listFilterGender = copyList,
                    )
                }
            }

            is Event.OnResetFilterButtonClicked -> {
                val genderCopyList = state.value.listFilterGender.toMutableList().apply {
                    val selectedItemIndex = indexOfFirst { it.selected }
                    val selectedItem = find { it.selected }

                    if (selectedItemIndex != 0) {
                        this[selectedItemIndex] = selectedItem!!.copy(selected = false)
                        this[0] = this[0].copy(selected = true)
                    }
                }

                val statusCopyList = state.value.listFilterStatus.toMutableList().apply {
                    val selectedItemIndex = indexOfFirst { it.selected }
                    val selectedItem = find { it.selected }

                    if (selectedItemIndex != 0) {
                        this[selectedItemIndex] = selectedItem!!.copy(selected = false)
                        this[0] = this[0].copy(selected = true)
                    }
                }

                updateState {
                    copy(
                        showBottomSheetDialog = false,
                        listFilterGender = genderCopyList,
                        listFilterStatus = statusCopyList,
                    )
                }
                getAllCharacters()
            }
        }
    }

    private fun getAllCharacters() {
        viewModelScope.launch {
            getAllCharacterUseCase().distinctUntilChanged().cachedIn(viewModelScope).collect {
                _charactersState.value = it
            }
        }
    }

    private fun getCharactersByName() {
        viewModelScope.launch {
            getAllCharacterUseCase(name = state.value.searchText).distinctUntilChanged()
                .cachedIn(viewModelScope).collect {
                    _charactersState.value = it
                }
        }
    }

    private fun getAllCharactersFilter() {
        val statusSelected = state.value.listFilterStatus.find { it.selected }
        val genderSelected = state.value.listFilterGender.find { it.selected }

        viewModelScope.launch {
            getAllCharacterUseCase(
                status = statusSelected?.name,
                gender = genderSelected?.name,
            ).distinctUntilChanged()
                .cachedIn(viewModelScope).collect {
                    _charactersState.value = it
                }
        }
    }

    data class FilterStatus(
        var name: String,
        var selected: Boolean,
    )

    data class FilterGender(
        val name: String,
        val imageVector: ImageVector,
        var selected: Boolean,
    )
}

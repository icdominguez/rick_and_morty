package com.icdominguez.data.characters.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.icdominguez.core.model.Character
import com.icdominguez.core.model.CharacterDetails
import com.icdominguez.core.network.RAMNetworkDataSource
import com.icdominguez.data.characters.getPageIntFromUrl
import com.icdominguez.data.characters.toCharacter
import com.icdominguez.data.characters.toCharacterDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DefaultCharactersRepository @Inject constructor(
    private val dataSource: RAMNetworkDataSource,
) : CharactersRepository {

    override suspend fun getAllCharacters(name: String?, status: String?, gender: String?): Flow<PagingData<Character>> {
        return Pager(PagingConfig(pageSize = 20)) {
            CharactersPagingSource(
                dataSource = dataSource,
                name = name,
                status = status,
                gender = gender,
            )
        }.flow
    }

    override suspend fun getCharacterDetails(characterId: Int): Result<CharacterDetails> {
        return withContext(Dispatchers.IO) {
            try {
                Result.success(dataSource.getCharacterDetails(characterId).toCharacterDetails())
            } catch (throwable: Throwable) {
                Result.failure(throwable)
            }
        }
    }
}

class CharactersPagingSource(
    private val name: String? = null,
    private val status: String? = null,
    private val gender: String? = null,
    private val dataSource: RAMNetworkDataSource,
) : PagingSource<Int, Character>() {
    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val pageNumber = params.key ?: 1
        return try {
            val charactersResponse = dataSource.getCharacters(pageNumber, name, status, gender)
            val characters = charactersResponse.results.map { getCharacterResult ->
                getCharacterResult.toCharacter()
            }

            val nextPage: Int? = getPageIntFromUrl(charactersResponse.info.next)
            LoadResult.Page(
                data = characters,
                nextKey = nextPage,
                prevKey = null,
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}

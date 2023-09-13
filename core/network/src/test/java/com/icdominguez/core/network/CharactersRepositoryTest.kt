package com.icdominguez.core.network

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.CoreMatchers.hasItem
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CharactersRepositoryTest : BaseTest() {
    private lateinit var charactersRepository: RAMNetworkDataSource

    @Before
    override fun setup() {
        super.setup()
        charactersRepository = RetrofitRAMNetwork(apiService)
    }

    @Test
    fun `GET CHARACTERS - given a valid page returns valid characters`() = runTest {
        val page = 1
        val actualCharacters = charactersRepository.getCharacters(page)

        val expectedCharacter = JsonData.characterResponse

        assertThat(actualCharacters.results, hasItem(expectedCharacter))
    }

    @Test
    fun `GET CHARACTERS - given a invalid page number return an error`() = runTest {
        try {
            charactersRepository.getCharacters(1000)
        } catch (e: Throwable) {
            assertThat(e.message, containsString("HTTP 404"))
        }
    }

    @Test
    fun `GET CHARACTER DETAILS - given a valid character id return character details`() = runTest {
        val characterDetails = charactersRepository.getCharacterDetails(1)

        assertThat(characterDetails.id, `is`(JsonData.characterResponse.id))
        assertThat(characterDetails.name, `is`(JsonData.characterResponse.name))
    }

    @Test
    fun `GET CHARACTER DETAILS - given an invalid character id returns an error`() = runTest {
        try {
            charactersRepository.getCharacterDetails(1000)
        } catch (e: Exception) {
            assertThat(e.message, containsString("HTTP 404"))
        }
    }
}

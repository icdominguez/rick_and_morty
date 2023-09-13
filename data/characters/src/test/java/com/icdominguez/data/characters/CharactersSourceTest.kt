package com.icdominguez.data.characters

import android.content.res.Resources.NotFoundException
import androidx.paging.PagingSource
import com.icdominguez.core.model.Character
import com.icdominguez.data.characters.fake.FakeCharactersRepository
import com.icdominguez.data.characters.fake.FakeData
import com.icdominguez.data.characters.repository.CharactersPagingSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CharactersSourceTest {
    private lateinit var charactersSource: CharactersPagingSource

    @Before
    fun setUp() {
        charactersSource = CharactersPagingSource(dataSource = FakeCharactersRepository())
    }

    @Test
    fun `character paging source refresh is success`() = runTest {
        // given
        val actualResult = charactersSource.load(
            PagingSource.LoadParams.Refresh(
                key = 1,
                loadSize = 20,
                placeholdersEnabled = false,
            ),
        )

        // when
        val expectedResult = PagingSource.LoadResult.Page(
            data = FakeData.page1Characters.results.map { it.toCharacter() },
            prevKey = null,
            nextKey = 2,
        )

        // then
        print(actualResult)
        assertThat(actualResult, `is`(expectedResult))
    }

    @Test
    fun `characters load next page is a success`() = runTest {
        // given
        val actualResult = charactersSource.load(
            PagingSource.LoadParams.Append(
                key = 2,
                loadSize = 20,
                placeholdersEnabled = true,
            ),
        )

        // when
        val expectedResult = PagingSource.LoadResult.Page(
            data = FakeData.page2Characters.results.map { it.toCharacter() },
            prevKey = null,
            nextKey = 3,
        )

        // then
        assertThat(actualResult, `is`(expectedResult))
    }

    @Test
    fun `characters page with invalid page returns error`() = runTest {
        // given
        val actualResult = charactersSource.load(
            PagingSource.LoadParams.Refresh(
                key = 60,
                loadSize = 20,
                placeholdersEnabled = true,
            ),
        )

        // when
        val expectedResult = PagingSource.LoadResult.Error<Int, Character>(NotFoundException())

        // then
        assertThat(actualResult.toString(), `is`(expectedResult.toString()))
    }
}

package com.icdominguez.data.characters.di

import com.icdominguez.data.characters.repository.CharactersRepository
import com.icdominguez.data.characters.repository.DefaultCharactersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    fun bindCharactersRepository(
        defaultCharactersRepository: DefaultCharactersRepository,
    ): CharactersRepository
}

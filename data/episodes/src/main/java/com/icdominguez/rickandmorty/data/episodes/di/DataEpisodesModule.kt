package com.icdominguez.rickandmorty.data.episodes.di

import com.icdominguez.rickandmorty.data.episodes.repository.DefaultEpisodesRepository
import com.icdominguez.rickandmorty.data.episodes.repository.EpisodesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataEpisodesModule {
    @Binds
    fun bindEpisodeRepository(
        defaultEpisodesRepository: DefaultEpisodesRepository
    ): EpisodesRepository
}
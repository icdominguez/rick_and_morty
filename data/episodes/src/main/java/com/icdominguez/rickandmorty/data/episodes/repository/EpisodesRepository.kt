package com.icdominguez.rickandmorty.data.episodes.repository

import com.icdominguez.core.model.Episode

interface EpisodesRepository {
    suspend fun getEpisode(episodeId: Int): Result<Episode>
}

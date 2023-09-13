package com.icdominguez.rickandmorty.data.episodes.repository

import com.icdominguez.core.model.Episode
import com.icdominguez.core.network.RAMNetworkDataSource
import com.icdominguez.core.network.model.response.GetEpisodeResponse
import com.icdominguez.rickandmorty.data.episodes.toEpisode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DefaultEpisodesRepository @Inject constructor(
    private val dataSource: RAMNetworkDataSource
): EpisodesRepository {
    override suspend fun getEpisode(episodeId: Int): Result<Episode> {
        return withContext(Dispatchers.IO) {
            try {
                Result.success(dataSource.getEpisode(episodeId).toEpisode())
            } catch (throwable: Throwable) {
                Result.failure(throwable)
            }
        }
    }

}
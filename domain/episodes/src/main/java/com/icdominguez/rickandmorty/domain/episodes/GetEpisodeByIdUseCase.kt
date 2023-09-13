package com.icdominguez.rickandmorty.domain.episodes

import com.icdominguez.rickandmorty.data.episodes.repository.EpisodesRepository
import javax.inject.Inject

class GetEpisodeByIdUseCase @Inject constructor(
    private val episodesRepository: EpisodesRepository,
) {
    suspend operator fun invoke(episodeId: Int) =
        episodesRepository.getEpisode(episodeId)
}

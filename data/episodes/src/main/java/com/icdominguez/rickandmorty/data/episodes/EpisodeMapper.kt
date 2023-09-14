package com.icdominguez.rickandmorty.data.episodes

import com.icdominguez.core.model.Episode
import com.icdominguez.core.network.model.response.GetEpisodeResponse

fun GetEpisodeResponse.toEpisode(): Episode =
    Episode(
        name = this.name,
        episode = this.episode
    )
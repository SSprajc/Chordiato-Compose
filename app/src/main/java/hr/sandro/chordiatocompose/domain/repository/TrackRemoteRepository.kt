package hr.sandro.chordiatocompose.domain.repository

import hr.sandro.chordiatocompose.data.remote.dto.serp.SerpResponseDto
import hr.sandro.chordiatocompose.data.remote.dto.shazam.ShazamResponseDto

interface TrackRemoteRepository {

    suspend fun getSong(encodedSample: String): ShazamResponseDto

    suspend fun getChordsLink(songName: String, artist: String): SerpResponseDto

}
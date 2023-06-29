package hr.sandro.chordiatocompose.domain.repository

import hr.sandro.chordiatocompose.domain.model.Track
import kotlinx.coroutines.flow.Flow

interface TrackLocalRepository {

    fun getAllTracks(): Flow<List<Track>>

    fun getFavouriteTracks(): Flow<List<Track>>

    suspend fun insertTrack(track: Track): Long

    suspend fun updateTrack(track: Track)

}
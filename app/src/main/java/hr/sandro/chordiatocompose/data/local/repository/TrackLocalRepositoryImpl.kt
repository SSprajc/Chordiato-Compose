package hr.sandro.chordiatocompose.data.local.repository

import hr.sandro.chordiatocompose.data.local.TrackDao
import hr.sandro.chordiatocompose.data.util.toTrack
import hr.sandro.chordiatocompose.data.util.toTrackEntity
import hr.sandro.chordiatocompose.domain.model.Track
import hr.sandro.chordiatocompose.domain.repository.TrackLocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TrackLocalRepositoryImpl(
    private val dao: TrackDao,
) : TrackLocalRepository {

    override fun getAllTracks(): Flow<List<Track>> = flow {
        emit(dao.getAllTracks().map { it.toTrack() })
    }

    override fun getFavouriteTracks(): Flow<List<Track>> = flow {
        emit(dao.getFavouriteTracks().map { it.toTrack() })
    }

    override suspend fun insertTrack(track: Track): Long {
        return dao.insertTrack(track.toTrackEntity())
    }

    override suspend fun updateTrack(track: Track) {
        dao.updateTrack(track.toTrackEntity())
    }
}
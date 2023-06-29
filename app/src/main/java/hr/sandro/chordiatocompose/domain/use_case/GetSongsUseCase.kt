package hr.sandro.chordiatocompose.domain.use_case

import hr.sandro.chordiatocompose.domain.model.Track
import hr.sandro.chordiatocompose.domain.repository.TrackLocalRepository
import kotlinx.coroutines.flow.Flow

class GetSongsUseCase(
    private val repository: TrackLocalRepository,
) {

    operator fun invoke(onlyFavourites: Boolean): Flow<List<Track>> {
        return if (onlyFavourites) {
            repository.getFavouriteTracks()
        } else {
            repository.getAllTracks()
        }
    }
}
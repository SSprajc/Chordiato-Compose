package hr.sandro.chordiatocompose.domain.use_case

import hr.sandro.chordiatocompose.domain.model.Track
import hr.sandro.chordiatocompose.domain.repository.TrackLocalRepository

class ToggleFavouriteUseCase(
    private val repository: TrackLocalRepository,
) {

    suspend operator fun invoke(track: Track) {
        if (track.favourite == true) {
            track.favourite = false
            repository.updateTrack(track)
        } else {
            track.favourite = true
            repository.updateTrack(track)
        }
    }
}
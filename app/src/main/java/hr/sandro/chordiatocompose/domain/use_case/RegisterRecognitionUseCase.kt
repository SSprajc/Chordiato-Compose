package hr.sandro.chordiatocompose.domain.use_case

import hr.sandro.chordiatocompose.domain.model.Track
import hr.sandro.chordiatocompose.domain.repository.TrackLocalRepository

class RegisterRecognitionUseCase(
    private val repository: TrackLocalRepository,
) {

    suspend operator fun invoke(track: Track) {
        val isAdded = repository.insertTrack(track)
        if (isAdded == -1L) {
            track.lastPlayed = null
            repository.updateTrack(track)
        }
    }
}
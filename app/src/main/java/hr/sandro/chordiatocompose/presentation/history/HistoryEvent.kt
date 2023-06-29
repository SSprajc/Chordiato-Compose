package hr.sandro.chordiatocompose.presentation.history

import hr.sandro.chordiatocompose.domain.model.Track


sealed class HistoryEvent {
    object GetSongs : HistoryEvent()
    data class ToggleFavourite(val track: Track) : HistoryEvent()
}
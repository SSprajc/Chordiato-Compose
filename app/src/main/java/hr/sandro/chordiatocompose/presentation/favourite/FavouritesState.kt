package hr.sandro.chordiatocompose.presentation.favourite

import hr.sandro.chordiatocompose.domain.model.Track


data class FavouritesState(
    val tracks: List<Track> = emptyList(),
)

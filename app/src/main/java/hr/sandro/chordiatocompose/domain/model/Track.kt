package hr.sandro.chordiatocompose.domain.model

import java.time.LocalDate

data class Track(
    val id: Int,
    val name: String,
    val artist: String,
    var favourite: Boolean? = false,
    var link: String?,
    var lastPlayed: LocalDate?,
)

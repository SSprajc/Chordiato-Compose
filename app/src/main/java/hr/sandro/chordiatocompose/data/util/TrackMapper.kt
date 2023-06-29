package hr.sandro.chordiatocompose.data.util

import hr.sandro.chordiatocompose.domain.model.Track
import hr.sandro.chordiatocompose.data.local.entity.TrackEntity
import hr.sandro.chordiatocompose.data.remote.dto.shazam.ShazamResponseDto
import java.time.LocalDate

//Mappers remote
fun ShazamResponseDto.toTrack(): Track? {
    return track?.let {
        Track(
            it.key,
            it.title,
            it.subtitle,
            null,
            null,
            null
        )
    }
}

//Mappers local
fun TrackEntity.toTrack(): Track {
    return Track(
        id = id,
        name = name,
        artist = artist,
        favourite = favourite,
        link = link,
        lastPlayed = LocalDate.ofEpochDay(lastPlayed!!)
    )
}

fun Track.toTrackEntity(): TrackEntity {
    return TrackEntity(
        id,
        name,
        artist,
        favourite,
        link,
        lastPlayed?.toEpochDay() ?: LocalDate.now().toEpochDay()
    )
}

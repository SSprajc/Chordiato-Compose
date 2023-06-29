package hr.sandro.chordiatocompose.data.remote.dto.shazam

import com.google.gson.annotations.SerializedName

data class Genres(
    @SerializedName("primary") val primary : String?
)

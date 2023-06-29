package hr.sandro.chordiatocompose.data.remote.dto.shazam

import com.google.gson.annotations.SerializedName

data class Sections(
    @SerializedName("type") val type: String?,
    @SerializedName("metapages") val metapages: List<Metapages>?,
    @SerializedName("tabname") val tabname: String?,
    @SerializedName("metadata") val metadata: List<Metadata>?,
)
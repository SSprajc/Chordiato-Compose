package hr.sandro.chordiatocompose.data.remote.dto.serp


import com.google.gson.annotations.SerializedName

data class Sitelinks(
    @SerializedName("inline")
    val `inline`: List<Inline?>?,
)
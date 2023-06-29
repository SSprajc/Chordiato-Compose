package hr.sandro.chordiatocompose.data.remote.dto.serp


import com.google.gson.annotations.SerializedName

data class AboutThisResult(
    @SerializedName("source")
    val source: Source?,
)
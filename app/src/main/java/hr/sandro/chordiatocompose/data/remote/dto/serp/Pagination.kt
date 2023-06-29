package hr.sandro.chordiatocompose.data.remote.dto.serp

import com.google.gson.annotations.SerializedName

data class Pagination(
    @SerializedName("current")
    val current: Int?,
    @SerializedName("next")
    val next: String?,
    @SerializedName("other_pages")
    val otherPages: OtherPages?,
)
package hr.sandro.chordiatocompose.data.remote.dto.serp

import com.google.gson.annotations.SerializedName

data class SerpapiPagination(
    @SerializedName("current")
    val current: Int?,
    @SerializedName("next")
    val next: String?,
    @SerializedName("next_link")
    val nextLink: String?,
    @SerializedName("other_pages")
    val otherPages: OtherPages?,
)
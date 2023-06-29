package hr.sandro.chordiatocompose.data.remote.dto.shazam

import com.google.gson.annotations.SerializedName

data class Options(
    @SerializedName("caption") val caption: String?,
    @SerializedName("actions") val actions: List<Actions>?,
    @SerializedName("beacondata") val beacondata: Beacondata?,
    @SerializedName("image") val image: String?,
    @SerializedName("type") val type: String?,
    @SerializedName("listcaption") val listcaption: String?,
    @SerializedName("overflowimage") val overflowimage: String?,
    @SerializedName("colouroverflowimage") val colouroverflowimage: Boolean?,
    @SerializedName("providername") val providername: String?,
)

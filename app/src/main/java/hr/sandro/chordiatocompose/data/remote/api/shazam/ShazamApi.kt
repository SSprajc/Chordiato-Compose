package hr.sandro.chordiatocompose.data.remote.api.shazam

import hr.sandro.chordiatocompose.data.remote.dto.shazam.ShazamResponseDto
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ShazamApi {
    @Headers(
        "X-RapidAPI-Key: b111c9527bmsh481bdae5f21f558p175feejsna6bbda25faae",
        "X-RapidAPI-Host: shazam.p.rapidapi.com"
    )
    @POST("songs/detect")
    suspend fun detect(@Body encodedRawAudio: RequestBody): ShazamResponseDto

    companion object {
        const val SHAZAM_API_URL = "https://shazam.p.rapidapi.com/"
    }
}
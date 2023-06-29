package hr.sandro.chordiatocompose.data.remote.repository

import hr.sandro.chordiatocompose.data.remote.api.serp.SerpApi
import hr.sandro.chordiatocompose.data.remote.api.serp.SerpApi.Companion.SERP_API_KEY
import hr.sandro.chordiatocompose.data.remote.api.serp.SerpApi.Companion.SERP_API_RESULT_PAGES
import hr.sandro.chordiatocompose.data.remote.api.serp.SerpApi.Companion.SERP_API_SEARCH_ENGINE
import hr.sandro.chordiatocompose.data.remote.api.shazam.ShazamApi
import hr.sandro.chordiatocompose.data.remote.dto.serp.SerpResponseDto
import hr.sandro.chordiatocompose.data.remote.dto.shazam.ShazamResponseDto
import hr.sandro.chordiatocompose.domain.repository.TrackRemoteRepository
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody

class TrackRemoteRepositoryImpl(
    private val shazamApi: ShazamApi,
    private val serpApi: SerpApi,
) : TrackRemoteRepository {

    override suspend fun getSong(encodedSample: String): ShazamResponseDto {
        val mediaType = "text/plain".toMediaTypeOrNull()
        val requestBody = encodedSample.toRequestBody(mediaType)
        return shazamApi.detect(requestBody)
    }

    override suspend fun getChordsLink(songName: String, artist: String): SerpResponseDto {
        val query = "$songName $artist chords"
        return serpApi.internetSearch(SERP_API_SEARCH_ENGINE,
            SERP_API_KEY,
            query,
            SERP_API_RESULT_PAGES)
    }
}
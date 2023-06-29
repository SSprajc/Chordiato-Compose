package hr.sandro.chordiatocompose.domain.use_case


import hr.sandro.chordiatocompose.core.Resource
import hr.sandro.chordiatocompose.domain.repository.TrackRemoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class GetSheetUseCase(
    private val repository: TrackRemoteRepository,
) {

    operator fun invoke(songName: String, artist: String): Flow<Resource<String>> = flow {
        try {
            emit(Resource.Loading())
            val link = repository.getChordsLink(songName, artist).organicResults?.get(0)?.link
            emit(Resource.Success(link))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}
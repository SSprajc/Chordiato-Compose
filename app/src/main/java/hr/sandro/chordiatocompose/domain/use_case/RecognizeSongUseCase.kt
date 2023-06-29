package hr.sandro.chordiatocompose.domain.use_case

import hr.sandro.chordiatocompose.core.Resource
import hr.sandro.chordiatocompose.data.util.toTrack
import hr.sandro.chordiatocompose.domain.model.Track
import hr.sandro.chordiatocompose.domain.repository.TrackRemoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class RecognizeSongUseCase(
    private val repository: TrackRemoteRepository,
) {

    operator fun invoke(encodedSample: String): Flow<Resource<Track>> = flow {
        try {
            emit(Resource.Loading())
            val track = repository.getSong(encodedSample).toTrack()
            emit(Resource.Success(track))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}
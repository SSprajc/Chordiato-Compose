package hr.sandro.chordiatocompose.presentation.history

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hr.sandro.chordiatocompose.domain.model.Track
import hr.sandro.chordiatocompose.domain.use_case.GetSongsUseCase
import hr.sandro.chordiatocompose.domain.use_case.ToggleFavouriteUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val getSongsUseCase: GetSongsUseCase,
    private val toggleFavouriteUseCase: ToggleFavouriteUseCase,
) : ViewModel() {

    private val _state = mutableStateOf(HistoryState())
    val state = _state

    fun onEvent(event: HistoryEvent) {
        when (event) {
            is HistoryEvent.GetSongs -> {
                getTracks()
            }
            is HistoryEvent.ToggleFavourite -> {
                val track = event.track
                viewModelScope.launch {
                    toggleFavouriteUseCase(track)
                    getTracks()
                }
            }
        }
    }

    private var fetchJob: Job? = null

    fun getTracks() {
        fetchJob?.cancel()
        fetchJob = getSongsUseCase(false).onEach { result ->
            _state.value = HistoryState(tracks = emptyList())
            _state.value = HistoryState(tracks = result)
        }.launchIn(viewModelScope)
    }

    data class HistoryState(
        val isLoading: Boolean = false,
        val tracks: List<Track> = emptyList(),
    )
}
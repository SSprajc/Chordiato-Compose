package hr.sandro.chordiatocompose.presentation.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hr.sandro.chordiatocompose.domain.use_case.GetSongsUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(
    private val getSongsUseCase: GetSongsUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(FavouritesState())
    val state = _state.asStateFlow()

    init {
        getFavouriteTracks()
    }

    private var fetchJob: Job? = null

    private fun getFavouriteTracks() {
        fetchJob?.cancel()
        fetchJob = getSongsUseCase(true).onEach { result ->
            _state.emit(FavouritesState(tracks = result))
        }.launchIn(viewModelScope)

    }
}



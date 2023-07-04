 package hr.sandro.chordiatocompose.presentation.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hr.sandro.chordiatocompose.core.Resource
import hr.sandro.chordiatocompose.domain.model.Track
import hr.sandro.chordiatocompose.domain.use_case.GetSheetUseCase
import hr.sandro.chordiatocompose.domain.use_case.RecognizeSongUseCase
import hr.sandro.chordiatocompose.domain.use_case.RegisterRecognitionUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SheetViewModel @Inject constructor(
    private val recognizeSongUseCase: RecognizeSongUseCase,
    private val getSheetUseCase: GetSheetUseCase,
    private val registerRecognitionUseCase: RegisterRecognitionUseCase,
) : ViewModel() {

    var state by mutableStateOf(SheetState())

    fun onEvent(event: SheetEvent) {
        when (event) {
            is SheetEvent.Recognize -> {
                recognizeSongUseCase(event.encodedSample).onEach { result ->
                    when (result) {
                        is Resource.Success -> {
                            val track = result.data
                            if (track?.name != null) {
                                googleSearch(result.data)
                            } else {
                                //_state.emit(SheetState(error = "Error in recognizing song"))
                            }
                        }
                        is Resource.Error -> {
                            //_state.emit(SheetState(error = "Error in recognizing song"))
                        }
                        is Resource.Loading -> {
                            //_state.emit(SheetState(isLoading = true))
                        }
                    }
                }.launchIn(viewModelScope)
            }
            is SheetEvent.SetUrl -> {
                state = state.copy(sheetUrl = event.sheetUrl)
            }
        }
    }

    private fun googleSearch(track: Track) {
        getSheetUseCase(track.name, track.artist).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    val link = result.data
                    track.link = link
                    //_state.emit(SheetState(sheetUrl = link!!))
                    state = state.copy(sheetUrl = link ?: "")
                    viewModelScope.launch {
                        registerRecognitionUseCase(track)
                    }
                }
                is Resource.Error -> {
                }
                is Resource.Loading -> {

                }
            }
        }.launchIn(viewModelScope)
    }
}
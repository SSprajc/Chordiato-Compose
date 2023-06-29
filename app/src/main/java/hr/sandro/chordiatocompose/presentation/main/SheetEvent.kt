package hr.sandro.chordiatocompose.presentation.main

sealed class SheetEvent {
    data class Recognize(val encodedSample: String) : SheetEvent()
}

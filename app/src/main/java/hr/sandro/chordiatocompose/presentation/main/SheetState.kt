package hr.sandro.chordiatocompose.presentation.main

data class SheetState(
    val sheetUrl: String = "",
    val isLoading: Boolean = false,
    val error: String = "",
)

package hr.sandro.chordiatocompose.presentation.main

import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import hr.sandro.chordiatocompose.R
import hr.sandro.chordiatocompose.core.util.AudioRecorderUtility
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun SheetScreen(
    viewModel: SheetViewModel = hiltViewModel(),
    navController: NavController? = null,
    link: String? = null
) {
    navController
    link?.let { viewModel.onEvent(SheetEvent.SetUrl(it)) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.BottomEnd,
    ) {
        CustomWebView(url = viewModel.state.sheetUrl)
        FloatingActionButton(
            modifier = Modifier.padding(bottom = 88.dp, end = 24.dp),
            onClick = {
                GlobalScope.launch {
                    withContext(Dispatchers.IO) {
                        val encodedSample = AudioRecorderUtility.recordSample()
                        viewModel.onEvent(SheetEvent.Recognize(encodedSample))
                    }
                }
            }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_music_note_24),
                contentDescription = "Recognise"
            )
        }
    }
}

@Composable
fun CustomWebView(url: String) {
    AndroidView(
        update = {
            if (it.url != url) {
                it.loadUrl(url)
            }
        },
        factory = {
            WebView(it).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                webViewClient = WebViewClient()
                settings.javaScriptEnabled = true
                loadUrl(url)
            }
        }
    )
}

@Preview
@Composable
fun SheetPreview() {
    SheetScreen()
}
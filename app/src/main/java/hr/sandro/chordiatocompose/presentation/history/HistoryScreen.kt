package hr.sandro.chordiatocompose.presentation.history

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import hr.sandro.chordiatocompose.domain.model.Track
import hr.sandro.chordiatocompose.presentation.favourite.FavouritesViewModel
import hr.sandro.chordiatocompose.presentation.ui.theme.ChordiatoComposeTheme
import java.time.LocalDate

@Composable
fun HistoryScreen(
    viewModel: HistoryViewModel = hiltViewModel(),
) {
    val state = viewModel.state

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp, vertical = 24.dp)
    ) {
        items(state.tracks) { track ->
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ),
                modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
            ) {
                HistoryItem(
                    track,
                    onStarClicked = {
                        viewModel.onEvent(HistoryEvent.ToggleFavourite(track))
                    })
            }
        }
    }
}

@Composable
fun HistoryItem(
    track: Track = Track(1, "On the road again", "Willie Nelson", true, "asdads", LocalDate.MIN),
    onStarClicked: () -> Unit,
) {
    var isFavourite by remember { mutableStateOf(track.favourite) }
    Row(modifier = Modifier.padding(12.dp)) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = track.name,
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.W400,
                    textAlign = TextAlign.Center
                ),

                )
            Text(
                text = track.artist, style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Light,
                    textAlign = TextAlign.Center
                )
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            IconButton(onClick = {
                isFavourite = !(isFavourite ?: false)
                onStarClicked()
            }) {
                Icon(
                    imageVector = if (isFavourite == true) Icons.Filled.Star else Icons.Filled.StarBorder,
                    contentDescription = "Favourite",
                    modifier = Modifier.size(40.dp)
                )
            }
            Text(
                text = track.lastPlayed.toString(),
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Preview
@Composable
fun CardPreview() {
    ChordiatoComposeTheme {
        //HistoryItem()
    }
}
package hr.sandro.chordiatocompose.presentation.favourite

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import hr.sandro.chordiatocompose.domain.model.Track
import hr.sandro.chordiatocompose.presentation.history.HistoryItem
import hr.sandro.chordiatocompose.presentation.main.SheetViewModel
import hr.sandro.chordiatocompose.presentation.ui.theme.ChordiatoComposeTheme
import java.time.LocalDate

@Composable
fun FavouritesScreen(
    viewModel: FavouritesViewModel = hiltViewModel(),
    navController: NavController? = null,
) {
    val state = viewModel.state
    LazyColumn(modifier = Modifier.fillMaxSize().padding(horizontal = 8.dp, vertical = 24.dp)) {
        items(state.tracks) { track ->
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                ),
                modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
            ) {
                FavouritesItem(track, onItemClicked = {
                    navController?.navigate("sheet?link=$it")
                })
            }
        }
    }
}

@Composable
fun FavouritesItem(
    track: Track = Track(
        1,
        "On the road again",
        "Willie Nelson",
        true,
        "asdads",
        LocalDate.MIN
    ),
    onItemClicked: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .padding(12.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
            .clickable { onItemClicked(track.link ?: "") },
    ) {
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
                text = track.artist,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Light,
                    textAlign = TextAlign.Center
                )
            )

        }
        Icon(
            imageVector = Icons.Filled.MusicNote,
            contentDescription = ""

        )
    }
}

@Preview
@Composable
fun CardPreview() {
    ChordiatoComposeTheme {
        FavouritesScreen()
    }
}

package hr.sandro.chordiatocompose.presentation.shared

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import hr.sandro.chordiatocompose.domain.model.Track
import hr.sandro.chordiatocompose.presentation.ui.LocalSpacing
import hr.sandro.chordiatocompose.presentation.ui.spacing
import java.time.LocalDate

@Preview
@Composable
fun FavouriteItem(
    track: Track = Track(1, "asd", "asds", true, "", LocalDate.now()),
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier.weight(4f)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = track.name,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colors.onBackground,
                    modifier = Modifier
                        .weight(1f)
                        .wrapContentWidth(Alignment.CenterHorizontally)
                )

            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = track.name,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colors.onBackground,
                    modifier = Modifier
                        .weight(1f)
                        .wrapContentWidth(Alignment.CenterHorizontally)
                )

            }
        }
        Column(
            modifier = Modifier.weight(1f),

        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = track.name,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colors.onBackground,
                    modifier = Modifier.weight(1f)
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = track.name,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colors.onBackground,
                    modifier = Modifier
                        .weight(1f)
                        .wrapContentWidth(Alignment.End)
                )

            }
        }
    }
}
package com.icdominguez.feature.characters.composables.gridview

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.icdominguez.core.model.Character
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.placeholder.shimmer.ShimmerPlugin

@Composable
fun CharacterGridItem(
    character: Character,
    onCharacterClicked: (Int) -> Unit,
) {
    val statusColor = when (character.status) {
        "Alive" -> Color.Green
        "Dead" -> Color.Red
        else -> Color.Gray
    }

    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
            .clickable {
                onCharacterClicked(character.id)
            },
        shape = RoundedCornerShape(8.dp),
    ) {
        CoilImage(
            imageModel = { character.imageUrl },
            component = rememberImageComponent {
                ShimmerPlugin(
                    baseColor = Color.Gray,
                    highlightColor = Color.LightGray,
                )
            },
            failure = {
                Text(text = "image request failed")
            }
        )/*
        AsyncImage(
            modifier = Modifier
                .fillMaxSize(),
            model = character.imageUrl,
            contentDescription = "Personaje: ${character.name}",
            contentScale = ContentScale.Crop,
        )*/

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 8.dp,
                    vertical = 4.dp,
                ),
            text = character.name,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyLarge,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 8.dp,
                    end = 8.dp,
                    top = 4.dp,
                    bottom = 8.dp,
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = "Status:",
            )

            Box(
                modifier = Modifier
                    .size(10.dp)
                    .background(
                        color = statusColor,
                        shape = CircleShape,
                    ),
            )

            Text(
                text = character.status,
            )
        }
    }

    /*Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable {
                onCharacterClicked(character.id)
            },
        horizontalAlignment = Alignment.Start,
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxSize(),
            model = character.imageUrl,
            contentDescription = "Personaje: ${character.name}",
            contentScale = ContentScale.Crop,
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 8.dp,
                    vertical = 4.dp,
                ),
            text = character.name,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyLarge,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 8.dp,
                    end = 8.dp,
                    top = 4.dp,
                    bottom = 8.dp,
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = "Status:",
            )

            Box(
                modifier = Modifier
                    .size(10.dp)
                    .background(
                        color = statusColor,
                        shape = CircleShape,
                    ),
            )

            Text(
                text = character.status,
            )
        }
    }*/
}

@Composable
@Preview
fun CharacterGridItemPreview() {
    CharacterGridItem(
        character = Character(
            id = 1,
            name = "Rick Sanchez",
            status = "Alive",
            species = "Human",
            gender = "Male",
            imageUrl = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            origin = "Earth (C-137)",
        ),
        onCharacterClicked = {},
    )
}

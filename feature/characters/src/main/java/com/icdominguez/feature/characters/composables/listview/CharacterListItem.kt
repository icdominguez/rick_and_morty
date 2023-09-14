package com.icdominguez.feature.characters.composables.listview

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.icdominguez.core.model.Character

@Composable
fun CharacterCard(
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
            .clickable {
                onCharacterClicked(character.id)
            }.padding(
                bottom = 5.dp,
                end = 5.dp,
                start = 5.dp,
            ),
        shape = RoundedCornerShape(8.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxHeight(),
                model = character.imageUrl,
                contentDescription = "Character: ${character.name}",
            )

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(
                        horizontal = 10.dp,
                    ),
            ) {
                Text(
                    text = character.name,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )

                Spacer(
                    modifier = Modifier
                        .height(2.dp),
                )

                Text(
                    text = "Origin",
                    fontSize = 12.sp,
                )

                Text(
                    text = character.origin,
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = "Status",
                    fontSize = 12.sp,
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .background(
                                color = statusColor,
                                shape = CircleShape,
                            ),
                    )

                    Text(
                        text = character.status + " - " + character.species,
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun CharacterCardPreview() {
    CharacterCard(
        character = Character(
            id = 1,
            name = "Rick Sanchez",
            status = "Alive",
            species = "Human",
            gender = "Male",
            imageUrl = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            origin = "Earth (C-137)",
        ),
        onCharacterClicked = { },
    )
}

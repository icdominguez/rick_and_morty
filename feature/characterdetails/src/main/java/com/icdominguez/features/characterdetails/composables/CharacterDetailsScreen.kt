package com.icdominguez.features.characterdetails.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.PlayLesson
import androidx.compose.material.icons.outlined.PlayLesson
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.icdominguez.features.characterdetails.CharacterDetailsScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailsScreen(
    viewModel: CharacterDetailsScreenViewModel,
    uiEvent: (CharacterDetailsScreenViewModel.Event) -> Unit,
    onBackClick: () -> Unit,
) {
    val state = viewModel.state.collectAsState().value

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Character Details")
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "",
                        )
                    }
                },
            )
        },
    ) { paddingValues ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(paddingValues),
        ) {
            if (state.isLoading) {
                CircularProgressIndicator()
            }
            state.data?.let { characterDetails ->

                val statusColor = when (characterDetails.status) {
                    "Alive" -> Color.Green
                    "Dead" -> Color.Red
                    else -> Color.Gray
                }

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .size(200.dp)
                            .clip(
                                shape = CircleShape,
                            ),
                        model = characterDetails.image,
                        contentDescription = "",
                    )

                    Text(
                        text = characterDetails.name,
                        style = MaterialTheme.typography.displaySmall,
                        textAlign = TextAlign.Center,
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
                            text = characterDetails.status,
                        )
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 5.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceAround,
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Text(
                                text = characterDetails.species,
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.bodyMedium,
                            )
                            Text(
                                text = "Species",
                            )
                        }
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Text(
                                text = characterDetails.gender,
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.bodyMedium,
                            )
                            Text(text = "Gender")
                        }
                    }

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        shape = RoundedCornerShape(5.dp),
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp),
                        ) {
                            Column(
                                modifier = Modifier.padding(start = 10.dp),
                            ) {
                                Text(
                                    text = characterDetails.location,
                                    fontWeight = FontWeight.Bold,
                                    style = MaterialTheme.typography.bodySmall,
                                )
                                Text(
                                    text = "Location",
                                    style = MaterialTheme.typography.bodySmall,
                                )
                            }

                            Spacer(Modifier.weight(1f))

                            Column {
                                IconButton(
                                    modifier = Modifier.height(30.dp).height(30.dp),
                                    onClick = { /*TODO*/ },
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.KeyboardArrowRight,
                                        contentDescription = "",
                                    )
                                }
                            }
                        }
                    }

                    Card(
                        modifier = Modifier.padding(horizontal = 10.dp),
                        shape = RoundedCornerShape(5.dp),
                    ) {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.Transparent),
                        ) {
                            item {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 8.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
                                    Text(
                                        text = "Episodes Appeared In (${state.episodeList.count()})",
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.ExtraBold,
                                    )

                                    TextButton(onClick = {}) {
                                        Text(text = "View All Episodes", fontSize = 13.sp)
                                    }
                                }
                            }

                            items(state.episodeList) { episode ->
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 8.dp, vertical = 4.dp),
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.PlayLesson,
                                        contentDescription = null,
                                        modifier = Modifier.size(32.dp),
                                    )
                                    Column(
                                        modifier = Modifier.padding(start = 5.dp),
                                    ) {
                                        Text(text = episode.episode)
                                        Text(text = episode.name, fontWeight = FontWeight.Bold)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

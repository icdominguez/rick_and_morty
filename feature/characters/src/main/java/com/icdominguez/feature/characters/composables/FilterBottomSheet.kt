package com.icdominguez.feature.characters.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.icdominguez.feature.characters.CharactersScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterBottomSheet(
    bottomSheetState: SheetState,
    onDismissRequest: () -> Unit,
    onApplyClicked: () -> Unit,
    filterStatusValues: List<CharactersScreenViewModel.FilterStatus>,
    filterGenderValues: List<CharactersScreenViewModel.FilterGender>,
    onFilterStatusSelected: (String) -> Unit,
    onFilterGenderSelected: (String) -> Unit,
    onResetFilterButtonClicked: () -> Unit
) {
    val cardShape = RoundedCornerShape(size = 10.dp)

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = bottomSheetState,
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(text = "Filter")
            }

            Divider()

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
            ) {
                Text(text = "Status")
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
            ) {
                filterStatusValues.forEach { filterStatus ->
                    Card(
                        modifier = Modifier
                            .padding(end = 5.dp)
                            .clip(cardShape)
                            .border(
                                width = 2.dp,
                                color = if (filterStatus.selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
                                shape = cardShape,
                            )
                            .clickable {
                                onFilterStatusSelected(filterStatus.name)
                            },
                        border = if (filterStatus.selected) BorderStroke(2.dp, MaterialTheme.colorScheme.primary) else null,
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(10.dp),
                        ) {
                            Text(
                                text = filterStatus.name.lowercase()
                                    .replaceFirstChar { it.uppercase() },
                                style = MaterialTheme.typography.labelMedium,
                                color = if (filterStatus.selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface,
                            )
                        }
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
            ) {
                Text(text = "Gender")
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                filterGenderValues.forEach { filterGender ->
                    Card(
                        modifier = Modifier
                            .width(80.dp)
                            .height(100.dp)
                            .padding(end = 5.dp)
                            .border(
                                width = 2.dp,
                                color = if (filterGender.selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
                                shape = cardShape,
                            )
                            .clip(cardShape)
                            .clickable {
                                onFilterGenderSelected(filterGender.name)
                            },
                    ) {
                        Column(
                            modifier = Modifier.padding(10.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Image(
                                modifier = Modifier
                                    .width(60.dp)
                                    .height(60.dp),
                                imageVector = filterGender.imageVector,
                                contentDescription = "",
                                colorFilter = if (filterGender.selected) ColorFilter.tint(MaterialTheme.colorScheme.primary) else null,
                            )
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = filterGender.name,
                                style = MaterialTheme.typography.labelSmall,
                                textAlign = TextAlign.Center,
                                color = if (filterGender.selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface,
                            )
                        }
                    }
                }
            }

            Divider()

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                horizontalArrangement = Arrangement.SpaceAround,
            ) {
                TextButton(
                    onClick = onApplyClicked
                ) {
                    Text(text = "Apply")
                }
                TextButton(
                    onClick = onResetFilterButtonClicked
                ) {
                    Text(text = "Reset filter")
                }
            }
        }
    }
}

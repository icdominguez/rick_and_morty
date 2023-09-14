package com.icdominguez.rickandmorty.core.designsystem.component

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RAMTopAppBar(
    title: String,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = title)
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RAMTopAppBar(
    title: String,
    actionIcon: ImageVector,
    actionIconContentDescription: String?,
    onActionClick: () -> Unit = {},
) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = title)
        },
        actions = {
            IconButton(
                onClick = onActionClick,
            ) {
                Icon(
                    imageVector = actionIcon,
                    contentDescription = actionIconContentDescription,
                )
            }
        },
    )
}

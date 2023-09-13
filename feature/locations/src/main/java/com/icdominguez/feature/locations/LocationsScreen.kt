package com.icdominguez.feature.locations

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.icdominguez.rickandmorty.core.designsystem.component.ComingSoon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationsScreen() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Locations") }
            )
        },
    ) { padding ->
        ComingSoon(
            title = "Episodes",
            modifier = Modifier.padding(padding)
        )
    }
}

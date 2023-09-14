package com.icdominguez.rickandmorty.core.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.icdominguez.rickandmorty.core.designsystem.R

@Composable
fun ComingSoon(
    title: String,
    modifier: Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = "$title is coming soon!")
        Image(painter = painterResource(id = R.drawable.rick_and_morty_logo), contentDescription = "")
        Text(text = "Work in progress")
    }
}

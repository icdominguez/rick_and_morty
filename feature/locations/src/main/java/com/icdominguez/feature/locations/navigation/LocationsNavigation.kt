package com.icdominguez.feature.locations.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.icdominguez.feature.locations.LocationsScreen

const val locationsRoute = "locations_route"

fun NavController.navigateToLocations(navOptions: NavOptions) {
    this.navigate(locationsRoute, navOptions)
}

fun NavGraphBuilder.locationsScreen() {
    composable(route = locationsRoute) {
        LocationsScreen()
    }
}

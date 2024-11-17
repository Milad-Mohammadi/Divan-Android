package ac.divan.navigation

import ac.divan.data.remote.dto.menu.Content
import ac.divan.presentation.home.HomeScreen
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation

fun NavController.navigateToRootScreen(rootScreen: RootScreen) {
    navigate(rootScreen.route) {
        launchSingleTop = true
        restoreState = true
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
    }
}

@Composable
fun NavGraph(
    navController: NavHostController,
    data: List<Content>,
) {
    NavHost(
        navController = navController,
        startDestination = RootScreen.Home.route,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None }
    ) {
        addHomeRoute(navController, data)
    }
}

fun NavGraphBuilder.addHomeRoute(
    navController: NavController,
    data: List<Content>
) {
    navigation(
        route = RootScreen.Home.route,
        startDestination = LeafScreen.Home.route
    ) {
        showHome(navController, data)
    }
}

private fun NavGraphBuilder.showHome(
    navController: NavController,
    data: List<Content>,
) {
    composable(route = LeafScreen.Home.route) {
        HomeScreen(navController, data)
    }
}
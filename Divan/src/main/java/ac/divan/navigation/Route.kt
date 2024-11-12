package ac.divan.navigation

sealed class RootScreen(val route: String) {
    data object Home: RootScreen("home_root")
    data object Startups: RootScreen("startups_root")
    data object Discover: RootScreen("discover_root")
    data object About: RootScreen("about_root")
}

sealed class LeafScreen(val route: String) {
    data object Home : LeafScreen("home")
    data object Startups : LeafScreen("startups")
    data object Discover : LeafScreen("discover")
    data object About : LeafScreen("about")

}
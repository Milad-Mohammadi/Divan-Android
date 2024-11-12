package ac.divan.navigation

data class BottomNavigationItem(
    val title: String,
    val screen: RootScreen,
    val iconId: Int,
    val iconFillId: Int,
)

package ac.divan.navigation

import ac.divan.R
import android.content.Context

object NavigationBarItems {
    fun get(context: Context) = listOf(
        BottomNavigationItem(
            title = context.getString(R.string.home),
            screen = RootScreen.Home,
            iconId = R.drawable.ic_home,
            iconFillId = R.drawable.ic_home_filled,
        ),
        BottomNavigationItem(
            title = context.getString(R.string.startups),
            screen = RootScreen.Home,
            iconId = R.drawable.ic_home,
            iconFillId = R.drawable.ic_home_filled,
        ),
        BottomNavigationItem(
            title = context.getString(R.string.discover),
            screen = RootScreen.Home,
            iconId = R.drawable.ic_home,
            iconFillId = R.drawable.ic_home_filled,
        ),
        BottomNavigationItem(
            title = context.getString(R.string.about),
            screen = RootScreen.Home,
            iconId = R.drawable.ic_home,
            iconFillId = R.drawable.ic_home_filled,
        )
    )
}
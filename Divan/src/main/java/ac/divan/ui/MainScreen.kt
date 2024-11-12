package ac.divan.ui

import ac.divan.navigation.LeafScreen
import ac.divan.navigation.NavGraph
import ac.divan.navigation.NavigationBarItems
import ac.divan.navigation.navigateToRootScreen
import ac.divan.presentation.components.bottom_navigation.BottomNavigation
import ac.divan.presentation.components.bottom_navigation.BottomNavigationButton
import ac.divan.ui.theme.Dimens
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    var showBottomNavBar by remember { mutableStateOf(true) }
    var selectedBottomNavItemIndex by rememberSaveable { mutableIntStateOf(0) }

    navController.addOnDestinationChangedListener { _, destination, _ ->
        val bottomBarRoutes = listOf(LeafScreen.Home, LeafScreen.Startups, LeafScreen.Discover, LeafScreen.About)
        showBottomNavBar = bottomBarRoutes.firstOrNull { it.route == destination.route} != null
        if (showBottomNavBar) {
            selectedBottomNavItemIndex = when (destination.route) {
                LeafScreen.Home.route -> 0
                LeafScreen.Startups.route -> 1
                LeafScreen.Discover.route -> 2
                else -> 3
            }
        }
    }

    Scaffold(
        bottomBar = {
            if (showBottomNavBar) {
                BottomNavBar(
                    navController = navController,
                    selectedItemIndex = selectedBottomNavItemIndex,
                    onSelectedItemIndexChange = { selectedBottomNavItemIndex = it }
                )
            }
        },
        modifier = Modifier.fillMaxSize(),
    ) { paddingValues ->
        val customPadding = PaddingValues(
            start = 0.dp,
            top = 0.dp,
            end = 0.dp,
            bottom = if (showBottomNavBar) paddingValues.calculateBottomPadding() - Dimens.bottomNavigationHeight else 0.dp
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(customPadding)
        ) {
            NavGraph(
                navController = navController,
            )
        }
    }
}

@Composable
private fun BottomNavBar(
    navController: NavController,
    selectedItemIndex: Int,
    onSelectedItemIndexChange: (Int) -> Unit
) {
    val navItems = NavigationBarItems.get(LocalContext.current)

    BottomNavigation(
        items = {
            navItems.forEachIndexed { index, item ->
                BottomNavigationButton(
                    item = item,
                    isSelected = index == selectedItemIndex,
                    showDot = false, // TODO
                    onClick = {
                        onSelectedItemIndexChange(index)
                        navController.navigateToRootScreen(item.screen)
                    }
                )
            }
        },
    )
}
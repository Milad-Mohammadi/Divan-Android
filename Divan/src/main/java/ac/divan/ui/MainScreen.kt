package ac.divan.ui

import ac.divan.navigation.NavGraph
import ac.divan.navigation.NavigationBarItems
import ac.divan.navigation.navigateToRootScreen
import ac.divan.presentation.components.text.TextBodyMedium
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    var selectedNavItemIndex by rememberSaveable { mutableIntStateOf(0) }
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            val navItems = NavigationBarItems.get(LocalContext.current)
            ModalDrawerSheet(
                modifier = Modifier.width(screenWidth * 0.75f)
            ) {
                Text("Drawer title", modifier = Modifier.padding(16.dp))

                navItems.forEachIndexed { index, item ->
                    NavigationDrawerItem(
                        modifier = Modifier.padding(horizontal = 8.dp),
                        colors = NavigationDrawerItemDefaults
                            .colors(
                                selectedContainerColor = MaterialTheme
                                    .colorScheme
                                    .primary
                                    .copy(alpha = 0.1f)
                            ),
                        label = {
                            val isSelected = index == selectedNavItemIndex
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painter = painterResource(id = if (isSelected) item.iconFillId else item.iconId),
                                    contentDescription = null,
                                    tint = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground,
                                )
                                TextBodyMedium(
                                    text = item.title,
                                    color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground,
                                    textAlign = TextAlign.Center
                                )
                            }
                        },
                        selected = index == selectedNavItemIndex,
                        onClick = {
                            coroutineScope.launch { drawerState.close() }
                            selectedNavItemIndex = index
                            navController.navigateToRootScreen(item.screen)
                        }
                    )
                }
            }
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            NavGraph(
                navController = navController,
            )
        }
    }
}
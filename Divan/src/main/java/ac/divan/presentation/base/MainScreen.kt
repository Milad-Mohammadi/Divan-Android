package ac.divan.presentation.base

import ac.divan.R
import ac.divan.data.remote.dto.block.BlockType
import ac.divan.data.remote.dto.menu.DefaultMenuContent
import ac.divan.navigation.NavGraph
import ac.divan.presentation.base.components.NavDrawerItem
import ac.divan.presentation.components.text.TextBodySmall
import ac.divan.presentation.components.text.TextTitleLarge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(menu: DefaultMenuContent) {
    val navController = rememberNavController()
    var selectedNavItemSlug by rememberSaveable { mutableStateOf(menu.block.items.first().slug) }
    var selectedNavItemTitle by rememberSaveable { mutableStateOf(menu.block.items.first().title) }
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val navItems = menu.block.items

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier.width(screenWidth * 0.75f),
            ) {
                LazyColumn {
                    item {
                        TextTitleLarge(stringResource(R.string.app_name), modifier = Modifier.padding(16.dp))
                    }

                    navItems.forEachIndexed { _, item ->
                        when (item.type) {
                            BlockType.GROUP.slug -> {
                                item {
                                    TextBodySmall(item.title, modifier = Modifier.padding(16.dp).alpha(0.7f))
                                    item.sub_items?.forEachIndexed { _, subItem ->
                                        NavDrawerItem(
                                            slug = subItem.slug,
                                            icon = subItem.icon,
                                            title = subItem.title,
                                            isSelected = subItem.slug == selectedNavItemSlug,
                                            onSelect = {
                                                coroutineScope.launch { drawerState.close() }
                                                selectedNavItemSlug = subItem.slug
                                                selectedNavItemTitle = subItem.title
                                                // navController.navigateToRootScreen(item.screen)
                                            }
                                        )
                                    }
                                }
                            }
                            else -> {
                                item {
                                    NavDrawerItem(
                                        slug = item.slug,
                                        icon = item.icon,
                                        title = item.title,
                                        isSelected = item.slug == selectedNavItemSlug,
                                        onSelect = {
                                            coroutineScope.launch { drawerState.close() }
                                            selectedNavItemSlug = item.slug
                                            selectedNavItemTitle = item.title
                                            // navController.navigateToRootScreen(item.screen)
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    ) {
        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),

            topBar = {
                CenterAlignedTopAppBar(
                    modifier = Modifier.padding(8.dp).clip(RoundedCornerShape(16.dp)),
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                        titleContentColor = MaterialTheme.colorScheme.onBackground,
                    ),
                    title = {
                        TextTitleLarge(
                            text = selectedNavItemTitle,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1
                        )
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                coroutineScope.launch { drawerState.open() }
                            },
                            content = {
                                Icon(
                                    imageVector = Icons.Filled.Menu,
                                    contentDescription = "Localized description"
                                )
                            }
                        )
                    },
                    scrollBehavior = scrollBehavior,
                )
            },
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
            ) {
                NavGraph(
                    navController = navController,
                )
            }
        }
    }
}
package ac.divan.presentation.home

import ac.divan.data.remote.dto.board.BlockType
import ac.divan.data.remote.dto.content_pagination.RenderedDataItem
import ac.divan.data.remote.dto.menu.Content
import ac.divan.presentation.components.text.TextBodyMedium
import ac.divan.presentation.components.text.TextTitleLarge
import ac.divan.presentation.components.text.TextTitleMedium
import ac.divan.presentation.components.text.TextTitleSmall
import ac.divan.presentation.home.components.ProfileCard
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import timber.log.Timber


@Composable
fun HomeScreen(
    navController: NavController,
    data: List<Content>,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val items: LazyPagingItems<List<RenderedDataItem>> = viewModel.contentPaginatedState.collectAsLazyPagingItems()
    val initialLoading = items.loadState.refresh is LoadState.Loading && items.itemSnapshotList.items.isEmpty()

    LaunchedEffect(data) {
        viewModel.onEvent(HomeEvent.GetContent(data))
        Timber.i("data updated.")
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(2.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        state.sections.forEach { section ->
            when (section.type) {
                BlockType.HEADING.slug -> {
                    when (section.props.level) {
                        1 -> section.content.forEach { item -> item { TextTitleLarge(item.text ?: "") } }
                        2 -> section.content.forEach { item -> item { TextTitleMedium(item.text ?: "") } }
                        3 -> section.content.forEach { item -> item { TextTitleSmall(item.text ?: "") } }
                    }
                }

                BlockType.PARAGRAPH.slug -> section.content.forEach { item -> item { TextBodyMedium(item.text ?: "", modifier = Modifier.padding(vertical = 10.dp)) } }
                BlockType.PARAGRAPH.slug -> section.content.forEach { item -> item { TextBodyMedium(item.text ?: "", modifier = Modifier.padding(vertical = 10.dp)) } }
                BlockType.GRID_VIEW.slug -> {
                    items(items.itemCount) { index ->
                        val item = items[index]
                        item?.let {
                            ProfileCard(
                                modifier = Modifier.padding(bottom = 10.dp),
                                data = item
                            )
                        }
                    }
                }
                BlockType.TABLE.slug -> {
                    items(items.itemCount) { index ->
                        val item = items[index]
                        item?.let {
                            ProfileCard(
                                modifier = Modifier.padding(bottom = 10.dp),
                                data = item
                            )
                        }
                    }
                }
            }
        }
    }
}

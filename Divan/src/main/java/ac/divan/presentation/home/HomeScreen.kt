package ac.divan.presentation.home

import ac.divan.data.remote.dto.block.BlockType
import ac.divan.data.remote.dto.block.Content
import ac.divan.data.remote.dto.content_pagination.RenderedDataItem
import ac.divan.presentation.components.text.TextBodyLarge
import ac.divan.presentation.components.text.TextBodyMedium
import ac.divan.presentation.components.text.TextTitleLarge
import ac.divan.presentation.components.text.TextTitleMedium
import ac.divan.presentation.components.text.TextTitleSmall
import ac.divan.presentation.home.components.ProfileCard
import ac.divan.presentation.home.components.charts.AnimatedBarChart
import ac.divan.presentation.home.components.charts.AnimatedPieChart
import ac.divan.presentation.home.components.charts.ChartItem
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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

        state.blocks.forEach { block ->
            when (block.block.type) {
                BlockType.FORM_CHARTS.slug -> {
                    val fields = block.block.form.stats.fields
                    fields.forEach { field ->
                        item {
                            TextBodyLarge(text = field.title, modifier = Modifier.padding(top = 10.dp, bottom = 4.dp))

                            when (field.type) {
                                BlockType.DROP_DOWN.slug, BlockType.CHOICE.slug -> {
                                    // PieChart
                                    val pieData = field.readable_stats.map {
                                        it.key to ChartItem(
                                            value = it.value.toFloat(),
                                            color = block
                                                .block
                                                .settings
                                                .color[field.slug]?.get(it.key) ?: ""
                                        )
                                    }

                                    AnimatedPieChart(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(16.dp),
                                        data = pieData
                                    )
                                }
                                BlockType.MULTI_SELECT.slug -> {
                                    // BarChart
                                    val barData = field.readable_stats.map {
                                        it.key to ChartItem(
                                            value = it.value.toFloat(),
                                            color = block
                                                .block
                                                .settings
                                                .color[field.slug]?.get(it.key) ?: ""
                                        )
                                    }

                                    AnimatedBarChart(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(16.dp),
                                        data = barData
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

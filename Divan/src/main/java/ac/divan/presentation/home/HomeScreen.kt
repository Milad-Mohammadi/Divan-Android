package ac.divan.presentation.home

import ac.divan.data.remote.dto.block.BlockType
import ac.divan.data.remote.dto.block.Content
import ac.divan.data.remote.dto.content_pagination.RenderedDataItem
import ac.divan.presentation.components.text.TextBodyLarge
import ac.divan.presentation.components.text.TextBodyMedium
import ac.divan.presentation.components.text.TextTitleLarge
import ac.divan.presentation.components.text.TextTitleMedium
import ac.divan.presentation.components.text.TextTitleSmall
import ac.divan.presentation.home.components.Loading
import ac.divan.presentation.home.components.PaginatedListErrorItem
import ac.divan.presentation.home.components.ProfileCard
import ac.divan.presentation.home.components.charts.AnimatedBarChart
import ac.divan.presentation.home.components.charts.AnimatedPieChart
import ac.divan.presentation.home.components.charts.ChartItem
import ac.divan.presentation.home.components.table.TableCell
import ac.divan.presentation.home.components.table.TableHeaderCell
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    navController: NavController,
    data: List<Content>,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val items: LazyPagingItems<List<RenderedDataItem>> = viewModel.contentPaginatedState.collectAsLazyPagingItems()
    val initialLoading = items.loadState.refresh is LoadState.Loading
    val scrollState = rememberLazyListState()
    val horizontalTableScrollState = rememberScrollState()

    LaunchedEffect(data) {
        scrollState.scrollToItem(0)
        viewModel.onEvent(HomeEvent.GetContent(data))
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        state = scrollState
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

                BlockType.PARAGRAPH.slug -> {
                    section.content.forEach { item ->
                        item {
                            TextBodyMedium(item.text ?: "", modifier = Modifier.padding(vertical = 10.dp))
                        }
                    }
                }

                BlockType.GRID_VIEW.slug -> {
                    if (initialLoading) {
                        item {
                            Loading(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 24.dp)
                            )
                        }
                    } else {
                        items(items.itemCount) { index ->
                            val item = items[index]
                            item?.let {
                                ProfileCard(
                                    modifier = Modifier.padding(bottom = 10.dp),
                                    data = item
                                )
                            }
                        }

                        items.apply {
                            when {
                                loadState.refresh is LoadState.Error -> {
                                    val error = items.loadState.refresh as LoadState.Error
                                    item {
                                        PaginatedListErrorItem(
                                            message = error.error.localizedMessage!!,
                                            onRetry = { retry() }
                                        )
                                    }
                                }

                                loadState.append is LoadState.Loading -> {
                                    item {
                                        Loading(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(vertical = 10.dp)
                                        )
                                    }
                                }

                                loadState.append is LoadState.Error -> {
                                    val error = items.loadState.append as LoadState.Error
                                    item {
                                        PaginatedListErrorItem(
                                            message = error.error.localizedMessage!!,
                                            onRetry = { retry() }
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                BlockType.TABLE.slug -> {
                    if (initialLoading) {
                        item {
                            Loading(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 24.dp)
                            )
                        }
                    } else {
                        val headers = mutableListOf<String>()
                        val header = items[0]
                        header?.forEach { headers.add(it.title) }

                        stickyHeader {
                            Row(
                                modifier = Modifier
                                    .background(MaterialTheme.colorScheme.background)
                                    .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
                                    .background(Color.LightGray)
                                    .border(
                                        width = 1.dp,
                                        shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp),
                                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.4f)
                                    )
                                    .fillMaxWidth()
                                    .horizontalScroll(horizontalTableScrollState)
                            ) {
                                headers.forEach { header ->
                                    TableHeaderCell(
                                        text = header,
                                        modifier = Modifier.width(120.dp)
                                    )
                                }
                            }
                        }

                        item {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp))
                                    .border(
                                        width = 1.dp,
                                        shape = RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp),
                                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.4f)
                                    )
                                    .horizontalScroll(horizontalTableScrollState)
                            ) {
                                for (index in 0 until items.itemCount) {
                                    val item = items[index]
                                    Row(
                                        modifier = Modifier
                                            .background(
                                                MaterialTheme
                                                    .colorScheme
                                                    .onBackground
                                                    .copy(alpha = if (index % 2 == 0) 0f else 0.1f)
                                            )
                                    ) {
                                        item?.forEach {
                                            TableCell(
                                                text = it.value ?: "-",
                                                modifier = Modifier.width(120.dp)
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }

                    items.apply {
                        when {
                            loadState.refresh is LoadState.Error -> {
                                val error = items.loadState.refresh as LoadState.Error
                                item {
                                    PaginatedListErrorItem(
                                        message = error.error.localizedMessage!!,
                                        onRetry = { retry() }
                                    )
                                }
                            }

                            loadState.append is LoadState.Loading -> {
                                item {
                                    Loading(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 10.dp)
                                    )
                                }
                            }

                            loadState.append is LoadState.Error -> {
                                val error = items.loadState.append as LoadState.Error
                                item {
                                    PaginatedListErrorItem(
                                        message = error.error.localizedMessage!!,
                                        onRetry = { retry() }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        if (state.loadingBlocks) {
            item {
                Loading(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 24.dp)
                )
            }
        } else {
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
                                        val pieData = field.readableStats.map {
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
                                        val barData = field.readableStats.map {
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
}

package ac.divan.presentation.home

import ac.divan.data.remote.dto.block.BlockType
import ac.divan.data.remote.dto.menu.Content
import ac.divan.presentation.components.text.TextBodyMedium
import ac.divan.presentation.components.text.TextTitleLarge
import ac.divan.presentation.components.text.TextTitleMedium
import ac.divan.presentation.components.text.TextTitleSmall
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import timber.log.Timber


@Composable
fun HomeScreen(
    navController: NavController,
    data: List<Content>,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val scrollState = rememberScrollState()

    LaunchedEffect(data) {
        viewModel.onEvent(HomeEvent.GetContent(data))
        Timber.i("data updated.")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        state.sections.forEach { section ->
            when (section.type) {
                BlockType.HEADING.slug -> {
                    when (section.props.level) {
                        1 -> section.content.forEach { item -> TextTitleLarge(item.text ?: "") }
                        2 -> section.content.forEach { item -> TextTitleMedium(item.text ?: "") }
                        3 -> section.content.forEach { item -> TextTitleSmall(item.text ?: "") }
                    }
                }

                BlockType.PARAGRAPH.slug -> section.content.forEach { item -> TextBodyMedium(item.text ?: "", modifier = Modifier.padding(vertical = 10.dp)) }
            }
        }
    }
}

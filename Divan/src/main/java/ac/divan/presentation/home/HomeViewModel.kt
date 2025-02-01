package ac.divan.presentation.home

import ac.divan.data.remote.dto.block.BlockType
import ac.divan.data.remote.dto.block.Content
import ac.divan.data.remote.dto.content_pagination.RenderedDataItem
import ac.divan.data.remote.onError
import ac.divan.data.remote.onSuccess
import ac.divan.domain.use_case.DivanUseCases
import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class HomeViewModel @Inject constructor(
    private val application: Application,
    private val useCases: DivanUseCases
): AndroidViewModel(application) {

    var state by mutableStateOf(HomeState())
    private val _contentPaginatedState: MutableStateFlow<PagingData<List<RenderedDataItem>>> = MutableStateFlow(value = PagingData.empty())
    val contentPaginatedState: MutableStateFlow<PagingData<List<RenderedDataItem>>> get() = _contentPaginatedState

    fun onEvent(event: HomeEvent) {
        when(event) {
            is HomeEvent.GetContent -> getContent(event.content)
        }
    }

    private fun getContent(content: List<Content>) {
        state = state.copy(sections = content, blocks = emptyList())
        content.forEach {
            when (it.type) {
                BlockType.GRID_VIEW.slug -> {
                    it.props.data?.slug?.let { slug -> getContentPaginated(slug = slug) }
                }
                BlockType.TABLE.slug -> {
                    it.props.data?.slug?.let { slug -> getContentPaginated(slug = slug) }
                }
                BlockType.FORM_CHARTS.slug -> {
                    it.props.data?.slug?.let { slug -> getBlock(slug = slug) }
                }
            }
        }
    }

    private fun getContentPaginated(slug: String) = viewModelScope.launch {
        _contentPaginatedState.value = PagingData.empty()
        useCases
            .getBlockContent(slug)
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
            .collect { _contentPaginatedState.value = it }
    }

    private fun getBlock(slug: String) = viewModelScope.launch {
        state = state.copy(loadingBlocks = true)
        useCases
            .getBlock(slug)
            .onError { _, message -> /* TODO: Handle Error */ }
            .onSuccess {
                val newBlocks = state.blocks.plus(it.responseData)
                state = state.copy(blocks = newBlocks, loadingBlocks = false)
            }
    }
}
package ac.divan.presentation.home

import ac.divan.data.remote.dto.block.Content

sealed class HomeEvent {
    data class GetContent(val content: List<Content>): HomeEvent()
}
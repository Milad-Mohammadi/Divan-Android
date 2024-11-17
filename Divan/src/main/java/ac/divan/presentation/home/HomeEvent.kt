package ac.divan.presentation.home

import ac.divan.data.remote.dto.menu.Content

sealed class HomeEvent {
    data class GetContent(val content: List<Content>): HomeEvent()
}
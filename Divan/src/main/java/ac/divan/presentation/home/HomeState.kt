package ac.divan.presentation.home

import ac.divan.data.remote.dto.menu.Content

data class HomeState(
    val sections: List<Content> = emptyList()
)
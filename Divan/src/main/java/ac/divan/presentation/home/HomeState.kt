package ac.divan.presentation.home

import ac.divan.data.remote.dto.block.Content

data class HomeState(
    val sections: List<Content> = emptyList()
)
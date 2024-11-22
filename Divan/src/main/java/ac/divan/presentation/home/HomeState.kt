package ac.divan.presentation.home

import ac.divan.data.remote.dto.block.Content
import ac.divan.data.remote.dto.board.Block

data class HomeState(
    val sections: List<Content> = emptyList(),
    val blocks: List<Block> = emptyList()
)
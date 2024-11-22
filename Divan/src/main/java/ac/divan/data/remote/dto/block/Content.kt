package ac.divan.data.remote.dto.block

import ac.divan.data.remote.dto.board.ContentData
import ac.divan.data.remote.dto.board.Props

data class Content(
    val content: List<ContentData>,
    val id: String,
    val type: String,
    val props: Props,
)
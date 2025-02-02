package ac.divan.data.remote.dto.board

data class ContentData(
    val text: String?,
    val type: String,
    val href: String = ""
)
package ac.divan.data.remote.dto.menu

data class Content(
    val content: List<ContentData>,
    val id: String,
    val type: String,
    val props: Props,
)
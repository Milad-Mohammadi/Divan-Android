package ac.divan.data.remote.dto.menu

data class DefaultMenuContent(
    val block: DefaultMenuBlock,
) {
    data class DefaultMenuBlock(
        val items: List<DefaultMenuItem>,
    )

    data class DefaultMenuItem(
        val slug: String,
        val icon: String?,
        val title: String,
        val type: String,
        var sub_items: List<DefaultMenuItem>? = null
    )
}
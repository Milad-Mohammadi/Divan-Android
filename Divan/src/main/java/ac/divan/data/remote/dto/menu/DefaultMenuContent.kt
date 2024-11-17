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
        var content: List<Content>,
        var sub_items: List<DefaultMenuItem>? = null
    ) {
        fun findItemBySlug(targetSlug: String): DefaultMenuItem? {
            if (this.slug == targetSlug) return this
            return sub_items?.firstNotNullOfOrNull { it.findItemBySlug(targetSlug) }
        }
    }
}
package ac.divan.data.remote.dto.block

data class BlockContent(
    val items: List<BlockItem>,
    val form: BlockForm,
) {
    data class BlockItem(
        val slug: String,
        val icon: String?,
        val title: String,
        val type: String,
        var content: List<Content>,
        var sub_items: List<BlockItem>? = null
    ) {
        fun findItemBySlug(targetSlug: String): BlockItem? {
            if (this.slug == targetSlug) return this
            return sub_items?.firstNotNullOfOrNull { it.findItemBySlug(targetSlug) }
        }
    }

    data class BlockForm(
        val slug: String,
        val stats: FormStats,
    )

    data class FormStats(
        val total: Int,
        val fields: List<StatsField>,
    )

    data class StatsField(
        val type: String,
        val title: String,
        val readable_stats: Map<String, Int>,
    )
}
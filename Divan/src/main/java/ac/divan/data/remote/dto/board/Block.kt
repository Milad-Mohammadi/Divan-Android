package ac.divan.data.remote.dto.board

import ac.divan.data.remote.dto.menu.Content

data class Block(
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
        val fields: StatsFields,
    )

    data class StatsFields(
        val total: Int,
        val fields: List<StatsField>,
    )

    data class StatsField(
        val type: String,
        val title: String,
        val readable_stats: Map<String, Int>,
    )
}
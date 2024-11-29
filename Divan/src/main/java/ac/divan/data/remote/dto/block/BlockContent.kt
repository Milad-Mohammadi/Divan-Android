package ac.divan.data.remote.dto.block

import com.google.gson.annotations.SerializedName

data class BlockContent(
    val items: List<BlockItem>,
    val form: BlockForm,
    val type: String,
    val settings: BlockSettings,
) {
    data class BlockItem(
        val slug: String,
        val icon: String?,
        val title: String,
        val type: String,
        var content: List<Content>,
        @SerializedName("sub_items") var subItems: List<BlockItem>? = null
    ) {
        fun findItemBySlug(targetSlug: String): BlockItem? {
            if (this.slug == targetSlug) return this
            return subItems?.firstNotNullOfOrNull { it.findItemBySlug(targetSlug) }
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
        val slug: String,
        val type: String,
        val title: String,
        @SerializedName("readable_stats") val readableStats: Map<String, Int>,
    )

    data class BlockSettings(
        val color: Map<String, Map<String, String>>,
    )
}
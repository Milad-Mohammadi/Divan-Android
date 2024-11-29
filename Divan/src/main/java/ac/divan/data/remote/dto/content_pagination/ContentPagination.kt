package ac.divan.data.remote.dto.content_pagination

import com.google.gson.annotations.SerializedName


data class ContentPagination(
    val objects: List<RenderedObject>,
    val previous: String?,
    val next: String?,
    val count: Int,
    @SerializedName("page_size") val pageSize: Int,
    @SerializedName("page_count") val pageCount: Int,
    @SerializedName("current_page") val currentPage: Int,
    val columns: List<Column>,
) {
    fun mapAllRenderedData(): List<List<RenderedDataItem>> {
        return objects.map { renderedObject ->
            columns.mapNotNull { column ->
                renderedObject.renderedData[column.slug]?.let { dataItem ->
                    RenderedDataItem(
                        title = column.title,
                        value = dataItem.value,
                        rawValue = dataItem.rawValue,
                        type = dataItem.type
                    )
                }
            }
        }
    }

}
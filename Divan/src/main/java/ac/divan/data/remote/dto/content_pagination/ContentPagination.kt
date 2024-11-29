package ac.divan.data.remote.dto.content_pagination


data class ContentPagination(
    val objects: List<RenderedObject>,
    val previous: String?,
    val next: String?,
    val count: Int,
    val page_size: Int,
    val page_count: Int,
    val current_page: Int,
    val columns: List<Column>,
) {
    fun mapAllRenderedData(): List<List<RenderedDataItem>> {
        return objects.map { renderedObject ->
            columns.mapNotNull { column ->
                renderedObject.rendered_data[column.slug]?.let { dataItem ->
                    RenderedDataItem(
                        title = column.title,
                        value = dataItem.value,
                        type = dataItem.type
                    )
                }
            }
        }
    }

}
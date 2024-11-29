package ac.divan.data.remote.paging_source

import ac.divan.data.remote.DivanApi
import ac.divan.data.remote.dto.content_pagination.RenderedDataItem
import ac.divan.util.Constants
import androidx.paging.PagingSource
import androidx.paging.PagingState

class ContentPagingSource(
    private val api: DivanApi,
    private val slug: String
) : PagingSource<Int, List<RenderedDataItem>>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, List<RenderedDataItem>> {
        return try {
            val currentPage = params.key ?: 1
            val data = api.getContentPagination(
                slug = slug,
                page = currentPage,
                pageSize = Constants.CONTENT_PAGE_SIZE
            )

            val items = data.responseData.mapAllRenderedData()

            LoadResult.Page(
                data = items,
                prevKey = if (data.responseData.previous == null) null else data.responseData.currentPage - 1,
                nextKey = if (data.responseData.next == null) null else data.responseData.currentPage + 1
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, List<RenderedDataItem>>): Int? {
        return state.anchorPosition
    }
}
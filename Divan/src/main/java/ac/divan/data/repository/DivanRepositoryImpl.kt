package ac.divan.data.repository

import ac.divan.data.remote.DivanApi
import ac.divan.data.remote.dto.content_pagination.RenderedDataItem
import ac.divan.data.remote.handleApi
import ac.divan.data.remote.paging_source.ContentPagingSource
import ac.divan.domain.repository.DivanRepository
import ac.divan.util.Constants
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

class DivanRepositoryImpl(
    private val api: DivanApi,
): DivanRepository {

    override suspend fun getFormInfo() = handleApi {
        api.getFormInfo()
    }

    override suspend fun getBlock(slug: String) = handleApi {
        api.getBlock(slug)
    }

    override suspend fun getBlockContent(slug: String): Flow<PagingData<List<RenderedDataItem>>> {
        return Pager(
            config = PagingConfig(pageSize = Constants.CONTENT_PAGE_SIZE, prefetchDistance = 2),
            pagingSourceFactory = { ContentPagingSource(api, slug) }
        ).flow
    }

}
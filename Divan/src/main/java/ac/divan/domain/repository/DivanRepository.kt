package ac.divan.domain.repository

import ac.divan.data.remote.NetworkResult
import ac.divan.data.remote.dto.ApiResponse
import ac.divan.data.remote.dto.content_pagination.RenderedDataItem
import ac.divan.data.remote.dto.menu.DefaultMenuContent
import ac.divan.data.remote.dto.menu.DefaultMenuSlug
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface DivanRepository {
    suspend fun getFormInfo(): NetworkResult<ApiResponse<DefaultMenuSlug>>
    suspend fun getDefaultMenu(menuSlug: String): NetworkResult<ApiResponse<DefaultMenuContent>>
    suspend fun getBlockContent(slug: String): Flow<PagingData<List<RenderedDataItem>>>
}
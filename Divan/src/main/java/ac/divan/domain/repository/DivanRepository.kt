package ac.divan.domain.repository

import ac.divan.data.remote.NetworkResult
import ac.divan.data.remote.dto.ApiResponse
import ac.divan.data.remote.dto.board.Block
import ac.divan.data.remote.dto.content_pagination.RenderedDataItem
import ac.divan.data.remote.dto.menu.FormInfo
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface DivanRepository {
    suspend fun getFormInfo(): NetworkResult<ApiResponse<FormInfo>>
    suspend fun getBlock(slug: String): NetworkResult<ApiResponse<Block>>
    suspend fun getBlockContent(slug: String): Flow<PagingData<List<RenderedDataItem>>>
}
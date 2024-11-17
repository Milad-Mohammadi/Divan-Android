package ac.divan.domain.repository

import ac.divan.data.remote.NetworkResult
import ac.divan.data.remote.dto.ApiResponse
import ac.divan.data.remote.dto.menu.DefaultMenuContent
import ac.divan.data.remote.dto.menu.DefaultMenuSlug

interface DivanRepository {
    suspend fun getFormInfo(): NetworkResult<ApiResponse<DefaultMenuSlug>>
    suspend fun getDefaultMenu(menuSlug: String): NetworkResult<ApiResponse<DefaultMenuContent>>
}
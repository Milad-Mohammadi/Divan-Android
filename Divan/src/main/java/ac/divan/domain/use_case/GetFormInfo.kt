package ac.divan.domain.use_case

import ac.divan.data.remote.NetworkResult
import ac.divan.data.remote.dto.ApiResponse
import ac.divan.data.remote.dto.menu.DefaultMenuSlug
import ac.divan.domain.repository.DivanRepository

class GetFormInfo(
    private val repository: DivanRepository
) {
    suspend operator fun invoke(): NetworkResult<ApiResponse<DefaultMenuSlug>> {
        return repository.getFormInfo()
    }
}
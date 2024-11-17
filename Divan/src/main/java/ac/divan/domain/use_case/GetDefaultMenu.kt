package ac.divan.domain.use_case

import ac.divan.data.remote.NetworkResult
import ac.divan.data.remote.dto.ApiResponse
import ac.divan.data.remote.dto.menu.DefaultMenuContent
import ac.divan.domain.repository.DivanRepository

class GetDefaultMenu(
    private val repository: DivanRepository
) {
    suspend operator fun invoke(menuString: String): NetworkResult<ApiResponse<DefaultMenuContent>> {
        return repository.getDefaultMenu(menuString)
    }
}
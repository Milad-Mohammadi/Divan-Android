package ac.divan.domain.use_case

import ac.divan.data.remote.NetworkResult
import ac.divan.data.remote.dto.ApiResponse
import ac.divan.data.remote.dto.board.Block
import ac.divan.domain.repository.DivanRepository

class GetBlock(
    private val repository: DivanRepository
) {
    suspend operator fun invoke(slug: String): NetworkResult<ApiResponse<Block>> {
        return repository.getBlock(slug)
    }
}
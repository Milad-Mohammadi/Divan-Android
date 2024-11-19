package ac.divan.domain.use_case

import ac.divan.data.remote.dto.content_pagination.RenderedDataItem
import ac.divan.domain.repository.DivanRepository
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

class GetBlockContent(
    private val repository: DivanRepository
) {

    suspend operator fun invoke(slug: String): Flow<PagingData<List<RenderedDataItem>>> {
        return repository.getBlockContent(slug)
    }
}

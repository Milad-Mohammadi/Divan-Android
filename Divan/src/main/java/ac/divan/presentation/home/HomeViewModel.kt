package ac.divan.presentation.home

import ac.divan.data.remote.dto.menu.Content
import ac.divan.domain.use_case.DivanUseCases
import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
open class HomeViewModel @Inject constructor(
    private val application: Application,
    private val useCases: DivanUseCases
): AndroidViewModel(application) {

    var state by mutableStateOf(HomeState())

    fun onEvent(event: HomeEvent) {
        when(event) {
            is HomeEvent.GetContent -> getContent(event.content)
        }
    }

    private fun getContent(content: List<Content>) {
        state = state.copy(sections = content)
    }
}
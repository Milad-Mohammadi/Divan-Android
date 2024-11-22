package ac.divan.presentation.base

import ac.divan.R
import ac.divan.data.remote.dto.board.Board
import ac.divan.data.remote.onError
import ac.divan.data.remote.onSuccess
import ac.divan.domain.use_case.DivanUseCases
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
open class MainViewModel @Inject constructor(
    private val application: Application,
    private val useCases: DivanUseCases
): AndroidViewModel(application) {
    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error
    private val _defaultMenu = MutableStateFlow<Board?>(null)
    val defaultMenu: StateFlow<Board?> = _defaultMenu

    init {
        getDrawerSlug()
    }

    private fun getDrawerSlug() = viewModelScope.launch {
        useCases
            .getFormInfo()
            .onError { _, message -> _error.value = message }
            .onSuccess {
                val slug = it.responseData.board.defaultMenu?.slug
                slug?.let {
                    getDrawerContent(slug)
                } ?: kotlin.run {
                    _error.value = application.getString(R.string.no_data)
                }
            }
    }

    private fun getDrawerContent(slug: String) = viewModelScope.launch {
        useCases
            .getDefaultMenu(slug)
            .onError { _, message -> _error.value = message }
            .onSuccess {
                val data = it.responseData
                _error.value = null
                _defaultMenu.value = data
                _isLoading.value = false

                Timber.i(it.responseData.toString())
            }
    }
}
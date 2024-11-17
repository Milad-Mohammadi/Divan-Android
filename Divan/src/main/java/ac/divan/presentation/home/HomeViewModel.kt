package ac.divan.presentation.home

import ac.divan.domain.use_case.DivanUseCases
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
open class HomeViewModel @Inject constructor(
    private val application: Application,
    private val useCases: DivanUseCases
): AndroidViewModel(application) {

}
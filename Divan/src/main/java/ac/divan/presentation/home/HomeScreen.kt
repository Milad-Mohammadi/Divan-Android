package ac.divan.presentation.home

import ac.divan.data.remote.dto.menu.DefaultMenuContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController


@Composable
fun HomeScreen(
    navController: NavController,
    data: DefaultMenuContent.DefaultMenuItem,
    viewModel: HomeViewModel = hiltViewModel()
) {

    Column(modifier = Modifier.fillMaxSize()) {

    }
}

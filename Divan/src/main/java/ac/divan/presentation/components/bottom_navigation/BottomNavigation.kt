package ac.divan.presentation.components.bottom_navigation

import ac.divan.navigation.NavigationBarItems
import ac.divan.ui.theme.Dimens
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BottomNavigation(
    items: @Composable () -> Unit,
) {
    Card(
        modifier = Modifier
            .height(Dimens.bottomNavigationHeight)
            .fillMaxWidth(1f),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
        elevation = CardDefaults.cardElevation(defaultElevation = 16.dp),
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            items()
        }
    }
}

@Preview
@Composable
fun BottomNavigationPrev() {
    BottomNavigation(
        items = { NavigationBarItems.get(LocalContext.current).subList(0, 2) },
    )
}
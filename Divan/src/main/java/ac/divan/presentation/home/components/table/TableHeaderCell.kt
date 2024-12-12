package ac.divan.presentation.home.components.table

import ac.divan.presentation.components.text.TextBodyMedium
import ac.divan.ui.theme.Dimens
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun TableHeaderCell(text: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.padding(Dimens.normal),
    ) {
        TextBodyMedium(
            text = text,
            fontWeight = FontWeight.Bold,
        )
    }
}
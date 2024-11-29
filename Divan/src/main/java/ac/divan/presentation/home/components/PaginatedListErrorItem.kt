package ac.divan.presentation.home.components

import ac.divan.R
import ac.divan.presentation.components.button.ButtonOutlineSmall
import ac.divan.presentation.components.text.TextBodySmall
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun PaginatedListErrorItem(
    message: String,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TextBodySmall(text = message, modifier = Modifier.weight(1F))
        ButtonOutlineSmall(
            text = stringResource(id = R.string.retry),
            onClick = { onRetry() }
        )
    }
}
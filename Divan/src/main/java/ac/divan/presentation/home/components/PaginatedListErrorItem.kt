package ac.divan.presentation.home.components

import ac.divan.R
import ac.divan.presentation.components.button.ButtonOutlineSmall
import ac.divan.presentation.components.text.TextBodySmall
import ac.divan.ui.theme.Dimens
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
            .padding(Dimens.normal),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(Dimens.normal)
    ) {
        TextBodySmall(text = message, modifier = Modifier.weight(1F))
        ButtonOutlineSmall(
            text = stringResource(id = R.string.retry),
            onClick = { onRetry() }
        )
    }
}
package ac.divan.presentation.components.button

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter

@Composable
fun ButtonSurface(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    icon: Painter? = null,
    onClick: () -> Unit
) {
    ButtonPrimary(
        text = text,
        modifier = modifier,
        enabled = enabled,
        color = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface,
        icon = icon,
        onClick = onClick
    )
}